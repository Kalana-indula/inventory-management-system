import React, {JSX} from 'react'
import {LucideIcon} from "lucide-react";

type StatDetail = {
    title: string;
    amount: string;
    changePercentage: number;
    IconComponent: LucideIcon;
}

type StatCardProps = {
    title: string;
    primaryIcon: JSX.Element;
    details: StatDetail[];
    dateRange: string;
}

const StatCard = ({
                      title,
                      primaryIcon,
                      details,
                      dateRange,
                  }: StatCardProps) => {
    const formatPercentage = (value: number) => {
        const signal = value >= 0 ? "+" : "";
        return `${signal}${value.toFixed()}%`;
    };

    const getChangeColor = (value: number) => value >= 0 ? "text-green-500" : "text-red-500";

    return (
        <div className="md:row-span-1 xl:row-span-2 bg-white col-span-1 shadow-md rounded-2xl flex flex-col overflow-hidden">
            {/* Header */}
            <div className="flex-shrink-0">
                <div className="flex justify-between items-center mb-2 px-5 pt-4">
                    <h2 className="font-semibold text-lg text-gray-700 truncate">
                        {title}
                    </h2>
                    <span className="text-xs text-gray-400 whitespace-nowrap ml-2">
                        {dateRange}
                    </span>
                </div>
                <hr/>
            </div>

            {/* Body */}
            <div className="flex-1 flex flex-col justify-center px-5 py-4 min-h-0">
                <div className="flex items-start gap-4">
                    {/* Icon */}
                    <div className="rounded-full p-3 sm:p-4 sm:mt-3 bg-blue-50 border-sky-300 border-[1px] flex-shrink-0">
                        {primaryIcon}
                    </div>

                    {/* Details */}
                    <div className="flex-1 min-w-0">
                        {details.map((detail, index) => (
                            <React.Fragment key={index}>
                                <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-1 sm:gap-2 my-3">
                                    <span className="text-gray-500 text-sm truncate">
                                        {detail.title}
                                    </span>
                                    <div className="flex items-center justify-between sm:justify-end gap-2">
                                        <span className="font-bold text-gray-800 text-sm">
                                            {detail.amount}
                                        </span>
                                        <div className="flex items-center flex-shrink-0">
                                            <detail.IconComponent
                                                className={`w-3 h-3 sm:w-4 sm:h-4 mr-1 ${getChangeColor(detail.changePercentage)}`}
                                            />
                                            <span
                                                className={`font-medium text-xs sm:text-sm ${getChangeColor(detail.changePercentage)}`}
                                            >
                                                {formatPercentage(detail.changePercentage)}
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                {/* Horizontal line */}
                                {index < details.length - 1 && <hr className="my-2"/>}
                            </React.Fragment>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}
export default StatCard;