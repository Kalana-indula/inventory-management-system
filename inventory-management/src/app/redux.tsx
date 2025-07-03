"use client";

import { useRef, useState, useEffect } from "react";
import { combineReducers, configureStore } from "@reduxjs/toolkit";
import {
    TypedUseSelectorHook,
    useDispatch,
    useSelector,
    Provider,
} from "react-redux";
import globalReducer from "@/state";
import { api } from "@/state/api";
import { setupListeners } from "@reduxjs/toolkit/query";

import {
    persistStore,
    persistReducer,
    FLUSH,
    REHYDRATE,
    PAUSE,
    PERSIST,
    PURGE,
    REGISTER,
} from "redux-persist";
import { PersistGate } from "redux-persist/integration/react";
import createWebStorage from "redux-persist/lib/storage/createWebStorage";

/* REDUX PERSISTENCE */
const createNoopStorage = () => {
    return {
        getItem(_key: any) {
            return Promise.resolve(null);
        },
        setItem(_key: any, value: any) {
            return Promise.resolve(value);
        },
        removeItem(_key: any) {
            return Promise.resolve();
        },
    };
};

const storage =
    typeof window === "undefined"
        ? createNoopStorage()
        : createWebStorage("local");

const persistConfig = {
    key: "root",
    storage,
    whitelist: ["global"],
    timeout: 1000, // Add timeout to prevent infinite loading
};

const rootReducer = combineReducers({
    global: globalReducer,
    [api.reducerPath]: api.reducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

/* REDUX STORE */
export const makeStore = () => {
    return configureStore({
        reducer: persistedReducer,
        middleware: (getDefaultMiddleware) =>
            getDefaultMiddleware({
                serializableCheck: {
                    ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
                },
            }).concat(api.middleware),
        devTools: process.env.NODE_ENV !== "production",
    });
};

/* REDUX TYPES */
export type AppStore = ReturnType<typeof makeStore>;
export type RootState = ReturnType<AppStore["getState"]>;
export type AppDispatch = AppStore["dispatch"];
export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;

/* LOADING COMPONENT */
const LoadingFallback = () => (
    <div className="flex items-center justify-center w-full min-h-screen bg-gray-50">
        <div className="text-center">
            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto mb-4"></div>
            <p className="text-gray-600">Loading application...</p>
        </div>
    </div>
);

/* PROVIDER WITH TIMEOUT */
export default function StoreProvider({children,}: { children: React.ReactNode; }) {
    const [isClient, setIsClient] = useState(false);
    const [showFallback, setShowFallback] = useState(true);
    const storeRef = useRef<AppStore>();

    useEffect(() => {
        setIsClient(true);
        // Force hide loading after maximum 2 seconds
        const timer = setTimeout(() => {
            setShowFallback(false);
        }, 2000);

        return () => clearTimeout(timer);
    }, []);

    if (!isClient) {
        return <LoadingFallback />;
    }

    if (!storeRef.current) {
        storeRef.current = makeStore();
        setupListeners(storeRef.current.dispatch);
    }

    const persistor = persistStore(storeRef.current);

    return (
        <Provider store={storeRef.current}>
            <PersistGate
                loading={showFallback ? <LoadingFallback /> : null}
                persistor={persistor}
                onBeforeLift={() => {
                    setShowFallback(false);
                }}
            >
                {children}
            </PersistGate>
        </Provider>
    );
}