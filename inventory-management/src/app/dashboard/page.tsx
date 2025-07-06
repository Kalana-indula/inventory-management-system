'use client';

import CardPopularProducts from "@/app/dashboard/CardPopularProducts";
import CardPurchaseSummary from "@/app/dashboard/CardPurchaseSummary";

const Dashboard = () => {

    return (
        <>
            <div
                className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 xl:overflow-auto gap-10 bp-4 custom-grid-rows">
                <CardPopularProducts/>
                <div className="row-span-3 xl:row-span-6 bg-gray-500"/>
                <CardPurchaseSummary/>
                <div className="row-span-3 bg-gray-500"/>
                <div className="md:row-span-1 xl:row-span-2 bg-gray-500"/>
                <div className="md:row-span-1 xl:row-span-2 bg-gray-500"/>
                <div className="md:row-span-1 xl:row-span-2 bg-gray-500"/>
            </div>
        </>
    )
}
export default Dashboard;
