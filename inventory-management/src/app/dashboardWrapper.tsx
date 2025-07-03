"use client"

import {ReactNode, useEffect} from "react";
import StoreProvider, {useAppSelector} from "@/app/redux";
import Sidebar from "@/app/(components)/Sidebar";
import Navbar from "@/app/(components)/Navbar";


const DashboardLayout = ({children}: { children: ReactNode }) => {
    //fetch the state if the sidebar collapsed
    const isSidebarCollapsed = useAppSelector(
        (state) => state.global.isSidebarCollapsed);

    //fetch the state if darkmode is enabled
    const isDarkMode = useAppSelector(
        (state) => state.global.isDarkMode);

    //
    useEffect(() => {
        if (isDarkMode) {
            document.documentElement.classList.add("dark");
            document.documentElement.classList.remove("light");
        } else {
            document.documentElement.classList.add("light");
            document.documentElement.classList.remove("dark");
        }
    }, [isDarkMode]); // Added isDarkMode as dependency
    return (

        <div className={`${isDarkMode ? "dark" : "light"} flex bg-gray-50 text-gray-900 w-full min-h-screen`}>
            <Sidebar/>
            <main className={`flex flex-col w-full h-full py-7 bg-gray-50 
            ${isSidebarCollapsed ? "md:pl-24" : "md:pl-72"}`}>
                <Navbar/>
                {children}
            </main>
        </div>
    );
};

const DashboardWrapper = ({children}: { children: ReactNode }) => {
    return (
        <StoreProvider>
            <DashboardLayout>
                {children}
            </DashboardLayout>
        </StoreProvider>
    );
}
export default DashboardWrapper;
