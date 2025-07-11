'use client';

import {
    Archive,
    CircleDollarSign,
    ClipboardIcon,
    Layout,
    LucideIcon,
    Menu,
    SlidersHorizontal,
    User
} from "lucide-react";
import {useAppDispatch, useAppSelector} from "@/app/redux";
import {setIsSidebarCollapsed} from "@/state";
import {usePathname} from "next/navigation";
import Link from "next/link";

const Sidebar = () => {

// Define props interface for individual sidebar navigation links
    interface SidebarLinkProps {
        href: string;
        icon: LucideIcon;
        label: string;
        isCollapsed: boolean;
    }

    // Functional component for rendering individual sidebar navigation links
    const SidebarLink = ({
                             href,
                             icon: Icon,
                             label,
                             isCollapsed
                         }: SidebarLinkProps) => {

        // Get current page pathname from Next.js router
        const pathname = usePathname();

        // Determine if this link is currently active (matches current route or is dashboard when on home)
        const isActive = pathname === href || (pathname === "/" && href === "/dashboard");

        return (
            <Link href={href}>
                <div
                    className={`cursor-pointer flex items-center 
                ${isCollapsed ? "justify-center py-4" : "justify-start px-8 py-4"}
                hover:text-blue-500 hover:bg-blue-100 gap-3 transition-colors ${
                        isActive ? "bg-blue-200 text-white" : ""
                    }
                `}
                >
                    <Icon className="w-6 h-6 !text-gray-700"/>

                    <span
                    className={`${
                        isCollapsed ? "hidden" : "block"
                    } font-medium text-gray-700`}
                    >
                        {label}
                    </span>
                </div>
            </Link>
        )
    }

    // sidebar functionalities
    const dispatch = useAppDispatch();
    const isSidebarCollapsed = useAppSelector(
        (state) => state.global.isSidebarCollapsed
    );

    const toggleSidebar = () => {
        dispatch(setIsSidebarCollapsed(!isSidebarCollapsed));
    };

    // add features for sidebar
    const sidebarClassNames = `fixed flex flex-col ${isSidebarCollapsed ? "w-0 md:w-16" : "w-72 md:w-64"}
    bg-white transition-all duration-300 overflow-hidden h-full shadow-md z-40`;

    return (
        <>
            <div className={sidebarClassNames}>
                {/*md:justify-normal :- to make the content normal beyond medium sized screens*/}
                <div className={`flex gap-3 justify-between md:justify-normal items-center pt-8
                ${isSidebarCollapsed ? "px-5" : "px-8"}`}>
                    {/* logo*/}
                    <div>logo</div>
                    <h1 className={`${isSidebarCollapsed ? "hidden" : "block"} font-extrabold text-2xl`}>STOCK</h1>

                    <button className="md:hidden px-3 py-3 bg-gray-100 rounded-full hover:bg-blue-100"
                            onClick={toggleSidebar}>
                        <Menu className="w-4 h-4"/>
                    </button>
                </div>

                {/*    Links*/}
                <div className="flex-grow mt-8">
                    <SidebarLink
                        href="/dashboard"
                        icon={Layout}
                        label="Dashboard"
                        isCollapsed={isSidebarCollapsed}/>
                    <SidebarLink
                        href="/inventory"
                        icon={Archive}
                        label="Inventory"
                        isCollapsed={isSidebarCollapsed}/>
                    <SidebarLink
                        href="/products"
                        icon={ClipboardIcon}
                        label="Products"
                        isCollapsed={isSidebarCollapsed}/>
                    <SidebarLink
                        href="/users"
                        icon={User}
                        label="Users"
                        isCollapsed={isSidebarCollapsed}/>
                    <SidebarLink
                        href="/expenses"
                        icon={CircleDollarSign}
                        label="expenses"
                        isCollapsed={isSidebarCollapsed}/>
                    <SidebarLink
                        href="/settings"
                        icon={SlidersHorizontal}
                        label="Settings"
                        isCollapsed={isSidebarCollapsed}/>
                </div>

                {/*    Footer*/}
                <div className={`${isSidebarCollapsed ? "hidden" : "block"} mb-10`}>
                    <p className="text-center text-xs text-gray-500">
                        &copy; 2025 Stock App
                    </p>
                </div>
            </div>
        </>
    )
}
export default Sidebar;
