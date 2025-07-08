'use client'

import React, {useEffect, useState} from 'react'
import axios from "axios";
import {TrendingUp} from "lucide-react";
import {Bar, BarChart, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis} from "recharts";

type SalesSummary = {
    id: number;
    totalValue: number;
    changePercentage: number;
    date: string;
}

const CardSalesSummary = () => {

    //fetch data
    const [salesSummary, setSalesSummary] = useState<SalesSummary[]>([]);
    const [timeFrame, setTimeFrame] = useState("weekly");
    const [isLoading, setIsLoading] = useState(false);

    //fetch total value sum
    const totalValueSum = salesSummary.reduce((acc, curr) => acc + curr.totalValue, 0) || 0;

    //fetch average percentage
    const averageChangePercentage = salesSummary.reduce((acc, curr, _, array) => {
        return acc + curr.changePercentage! / array.length;
    }, 0) || 0;

    const highestValueData = salesSummary.reduce((acc,curr)=>{
        return acc.totalValue > curr.totalValue ? acc : curr;
    },salesSummary[0] || {});

    const highestValueDate = highestValueData.date ? new Date(highestValueData.date).toLocaleDateString("en-US",{
        month: "numeric",
        day:"numeric",
        year:"2-digit",
    }) :"N/A";

    useEffect(() => {
        getSalesSummaryData();
    }, []);

    const getSalesSummaryData = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/sales_summaries`);
            setSalesSummary(response.data);
            // console.log(response.data);
        } catch (error) {
            console.log(error);
        }

    }

    return (
        <div className="row-span-3 xl:row-span-6 bg-white shadow-md rounded-2xl flex flex-col overflow-hidden">
            {isLoading ? (
                <div className="m-5">Loading...</div>
            ) : (
                <>
                    {/*header*/}
                    <div className="flex-shrink-0">
                        <h2 className="text-lg font-semibold mb-2 px-7 pt-5">
                            Sales Summary
                        </h2>
                        <hr/>
                    </div>
                    {/*    body*/}
                    <div className="flex flex-col flex-1 min-h-0">
                        {/*body header*/}
                        <div className="flex justify-between items-center mb-6 mx-7 flex-shrink-0">
                            <div className="text-lg font-medium">
                                <p className="text-xs text-gray-400">Value</p>
                                <span className="text-2xl font-extrabold">
                                    ${(totalValueSum / 1000000).toLocaleString("en", {
                                    maximumFractionDigits: 2,
                                })}m
                                </span>
                                <span className="text-green-500 text-sm ml-2">
                                    <TrendingUp className="inline w-4 h-4 mr-1"/>
                                    {averageChangePercentage.toFixed(2)}%
                                </span>
                            </div>
                            {/*drop down menu*/}
                            <select
                                className="shadow-sm border border-gray-300 bg-white p-2 rounded"
                                value={timeFrame}
                                onChange={(e) => {
                                    setTimeFrame(e.target.value);
                                }}
                            >
                                <option value="daily">Daily</option>
                                <option value="weekly">Weekly</option>
                                <option value="monthly">Monthly</option>
                            </select>
                        </div>
                        {/*chart*/}
                        <div className="flex-1 px-7 min-h-0" style={{ minHeight: '200px' }}>
                            <ResponsiveContainer width="100%" height="100%">
                                <BarChart
                                    data={salesSummary}
                                    margin={{top: 0, right: 0, left: -25, bottom: 0}}
                                >
                                    <CartesianGrid strokeDasharray="" vertical={false}/>
                                    <XAxis dataKey="date" tickFormatter={(value) => {
                                        const date = new Date(value);
                                        return `${date.getMonth() + 1}/${date.getDate()}`
                                    }}/>
                                    <YAxis tickFormatter={(value) => {
                                        return `$ ${(value / 1000000).toFixed(0)}m`;
                                    }}
                                           tick={{fontSize: 12, dx: -1}}
                                           tickLine={false}
                                           axisLine={false}
                                    />
                                    <Tooltip
                                        formatter={(value: number) => [
                                            `$${value.toLocaleString("en")}`,
                                        ]}
                                    />
                                    <Bar
                                        dataKey="totalValue"
                                        fill="#3182ce"
                                        barSize={10}
                                        radius={[10, 10, 0, 0]}
                                    />
                                </BarChart>
                            </ResponsiveContainer>
                        </div>
                    </div>
                    {/*    footer*/}
                    <div className="flex-shrink-0">
                        <hr/>
                        <div className="flex justify-between items-center mt-6 text-sm px-7 mb-4">
                            <p>{salesSummary.length || 0} days</p>
                            <p className="text-sm">
                                Highest Sales Date : {" "}
                                <span className="font-bold">{highestValueDate}</span>
                            </p>
                        </div>
                    </div>
                </>)
            }
        </div>
    )
}
export default CardSalesSummary;