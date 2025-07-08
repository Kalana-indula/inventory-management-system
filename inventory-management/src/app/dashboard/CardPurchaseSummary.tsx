'use client'

import React, {useEffect, useState} from 'react'
import axios from "axios";
import numeral from "numeral";
import {TrendingDown, TrendingUp} from "lucide-react";
import {Area, AreaChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from "recharts";

type PurchaseSummary = {
    id: number;
    totalPurchased: number;
    changePercentage: number;
    date: string;
}

const CardPurchaseSummary = () => {

    const [isLoading, setIsLoading] = useState(false);
    //purchase summary data
    const [purchaseSummary, setPurchaseSummary] = useState<PurchaseSummary[]>([]);

    const lastDataPoint = purchaseSummary[purchaseSummary.length - 1] || null;

    useEffect(() => {
        getPurchaseSummaryData();
    }, []);

    const getPurchaseSummaryData = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/purchase-summaries`);
            console.log(response.data);
            setPurchaseSummary(response.data);
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <div
            className="flex flex-col row-span-2 xl:row-span-3 col-span-1 md:col-span-2 xl:col-span-1 bg-white shadow-md rounded-2xl overflow-hidden">

            {isLoading ? (
                <div className="m-5">Loading....</div>
            ) : (
                <>
                    {/*header*/}
                    <div className="flex-shrink-0">
                        <h2 className="text-lg font-semibold mb-2 px-7 pt-5">
                            Purchase summary
                        </h2>
                        <hr/>
                    </div>

                    {/*    body*/}
                    <div className="flex flex-col flex-1 min-h-0">
                        {/*Body header*/}
                        <div className="mb-4 mt-7 px-7 flex-shrink-0">
                            <p className="text-xs text-gray-400">Purchased</p>
                            <div className="flex items-center">
                                <p className="text-2xl font-bold">
                                    {lastDataPoint
                                        ? numeral(lastDataPoint.totalPurchased).format("$0.00a") : "0"}
                                </p>
                                {lastDataPoint && (
                                    <p
                                        className={`text-sm ${
                                            lastDataPoint.changePercentage! >= 0
                                                ? "text-green-500"
                                                : "text-red-500"
                                        } flex ml-3`}
                                    >
                                        {lastDataPoint.changePercentage! >= 0 ? (
                                            <TrendingUp className="w-5 h-5 mr-1"/>
                                        ) : (
                                            <TrendingDown className="w-5 h-5 mr-1"/>
                                        )}
                                        {Math.abs(lastDataPoint.changePercentage!)}%
                                    </p>
                                )}
                            </div>
                        </div>
                        {/*    chart*/}
                        <div className="flex-1 px-2 pb-4 min-h-0" style={{ minHeight: '160px' }}>
                            <ResponsiveContainer width="100%" height="100%" className="p-2">
                                <AreaChart
                                    data={purchaseSummary}
                                    margin={{top: 0, right: 0, left: -50, bottom: 45}}
                                >
                                    <XAxis dataKey="date" tick={false} axisLine={false}/>
                                    <YAxis tickLine={false} tick={false} axisLine={false}/>
                                    <Tooltip
                                        formatter={(value: number) => [
                                            `$${value.toLocaleString("en")}`,
                                        ]}
                                        labelFormatter={(label) => {
                                            const date = new Date(label);
                                            return date.toLocaleDateString("en-US", {
                                                year: "numeric",
                                                month: "long",
                                                day: "numeric",
                                            });
                                        }}
                                    />
                                    <Area
                                        type="linear"
                                        dataKey="totalPurchased"
                                        stroke="#8884d8"
                                        fill="#8884d8"
                                        dot={true}
                                    />
                                </AreaChart>
                            </ResponsiveContainer>
                        </div>
                    </div>
                </>
            )}
        </div>
    )
}
export default CardPurchaseSummary;