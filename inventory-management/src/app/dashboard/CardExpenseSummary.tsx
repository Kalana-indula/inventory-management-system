'use client'

import React, {useEffect, useState} from 'react'
import axios from "axios";
import {Cell, Pie, PieChart, ResponsiveContainer} from "recharts";
import {TrendingUp} from "lucide-react";

type Category = {
    id: number;
    name: string;
}

type Expense = {
    id: number;
    amount: number;
    date: string;
    category: Category;
}

type ExpenseSums = {
    [category: string]: number;
}
const colors = ["#00C49F", "#0088EF", "#FFBB28"];

const CardExpenseSummary = () => {

    //fetch expense data
    const [expenseSummaryData, setExpenseSummaryData] = useState<Expense[]>([]);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        getExpenseSummaryData();
    }, []);

    const getExpenseSummaryData = async () => {
        try {
            setIsLoading(true);
            const response = await axios.get(`http://localhost:8080/api/expenses`);
            setExpenseSummaryData(response.data);
            // console.log(response.data);
        } catch (error) {
            console.log(error);
        } finally {
            setIsLoading(false);
        }
    }

    //process expense data to group by category and sum amounts
    const expenseSums = expenseSummaryData.reduce(
        (acc: ExpenseSums, expense: Expense) => {
            const category = expense.category.name + "Expenses";
            const amount = expense.amount;
            if (!acc[category]) acc[category] = 0;
            acc[category] += amount;
            return acc;
        },
        {}
    )

    //convert to format needed for the chart
    const expenseCategories = Object.entries(expenseSums).map(
        ([name, value]) => ({
            name,
            value,
        })
    );

    const totalExpenses = expenseCategories.reduce(
        (acc, category: { value: number }) => acc + category.value, 0
    );

    const formattedTotalExpenses = totalExpenses.toFixed(2);

    return (
        <div className="row-span-3 bg-white shadow-md rounded-2xl flex flex-col justify-between">
            {isLoading ? (
                <div className="m-5">Loading...</div>
            ) : (
                <>
                    {/*header*/}
                    <div className="flex-shrink-0">
                        <h2 className="text-lg font-semibold mb-2 px-7 pt-5">
                            Expense summary
                        </h2>
                        <hr/>
                    </div>
                    {/*body*/}
                    <div className="xl:flex justify-between pr-7">
                        {/*chart*/}
                        <div className="relative basis-3/5">
                            <ResponsiveContainer width="100%" height={140}>
                                <PieChart>
                                    <Pie data={expenseCategories} innerRadius={50} outerRadius={60} fill="#8884d8"
                                         dataKey="value"
                                         nameKey="name"
                                         cx="50%"
                                         cy="50%"
                                    >
                                        {expenseCategories.map((entry, index) => (
                                            <Cell
                                                key={`cell-${index}`}
                                                fill={colors[index % colors.length]}
                                            />
                                        ))}
                                    </Pie>
                                </PieChart>
                            </ResponsiveContainer>
                            <div className="absolute top-1/2 left-1/2 transform
                                -translate-x-1/2
                                -translate-y-1/2
                                text-center
                                basis-2/5">
                                    <span className="font-bold text-xl">
                                        ${formattedTotalExpenses}
                                    </span>
                            </div>
                        </div>
                    {/*    labels*/}
                        <ul className="flex flex-col justify-around items-center xl:items-start py-5 gap-3">
                            {
                                expenseCategories.map((entry,index)=>(
                                    <li key={`legend-${index}`} className="flex items-center text-xs">
                                        <span
                                            className="mr-2 w-3 h-3 rounded-full"
                                            style={{backgroundColor:colors[index % colors.length]}}
                                        ></span>
                                        {entry.name}
                                    </li>
                                ))}
                        </ul>
                    </div>
                {/*    footer*/}
                    <div>
                        <hr/>
                        {expenseSummaryData && (
                            <div className="mt-3 flex justify-between items-center px-7 mb-4">
                                <div className="pt-2">
                                    <p className="text-sm">
                                        Average : {" "}
                                        <span className="font-semibold">
                                            ${(totalExpenses/expenseCategories.length).toFixed(2)}
                                        </span>
                                    </p>
                                </div>
                                <span className="flex items-center mt-2">
                                    <TrendingUp className="mr-2 text-green-500"/>
                                    30%
                                </span>
                            </div>
                        )}
                    </div>
                </>
            )}

        </div>
    )
}
export default CardExpenseSummary
