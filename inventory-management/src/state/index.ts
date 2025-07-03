import {createSlice, PayloadAction} from "@reduxjs/toolkit";

export interface InitialStateTypes{
    isSidebarCollapsed: boolean;
    isDarkMode: boolean;
}

//Indicates if the sidebar is collapsed or theme is darkmode
const initialState:InitialStateTypes = {
    isSidebarCollapsed: false,
    isDarkMode: false,
};

export const globalSlice = createSlice({
    name: "global",
    initialState,
    reducers:{
        //change global store state
        //used when changing the sidebar to collapse
        setIsSidebarCollapsed:(state, action:PayloadAction<boolean>)=>{
            state.isSidebarCollapsed = action.payload;

        },

        //used when need to switch between darkmode
        setIsDarkMode:(state, action:PayloadAction<boolean>)=>{
            state.isDarkMode=action.payload;
        },
    },
});

export const {setIsSidebarCollapsed,setIsDarkMode}=globalSlice.actions;

export default globalSlice.reducer;