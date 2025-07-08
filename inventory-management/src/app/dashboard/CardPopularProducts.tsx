'use client'

import {use, useEffect, useState} from "react";
import {ShoppingBag} from "lucide-react";
import axios from "axios";
import Rating from "@/app/(components)/Rating";

type Products = {
    id: number;
    name: string;
    price: number;
    rating: number;
    stockQuantity: number;
}

const CardPopularProducts = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [products, setProducts] = useState<Products[]>([]);

    //load data at page loading
    useEffect(() => {
        getProductDetails();
    }, []);
    //fetching product data

    const getProductDetails = async ()=>{

        try{
            const response= await axios.get(`http://localhost:8080/api/products`);
            // console.log(response.data);
            setProducts(response.data);
        }catch(error){
            console.log(error);
        }

    }
    return (
        <>
            <div className="row-span-3 xl:row-span-6 bg-white shadow-md rounded-2xl pb-4 overflow-auto">
                {isLoading ? (
                    <div className="m-5"> Loading... </div>
                ) : (
                    <div>
                        <h3 className="text-lg font-semibold px-7 pt-5 pb-2">
                            Popular products
                        </h3>
                        <hr/>
                        <div className="overflow-auto h-full">
                            {products && products.slice(0,6).map((product) => (
                                <div
                                    key={product.id}
                                    className="flex items-center justify-between gap-3 px-5 py-7 border-b"
                                >
                                    <div className="flex items-center gap-3">
                                        <div>img</div>
                                        <div className="flex flex-col justify-between gap-1">
                                            <div className="font-bold text-gray-700">{product.name}</div>
                                            <div className="flex text-sm items-center">
                                            <span className="font-bold text-blue-500 text-xs">
                                                ${product.price}
                                            </span>
                                                <span className="mx-2">|</span>
                                                <div>
                                                    <Rating rating={product.rating || 0}/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="text-xs flex items-center">
                                        <button className="p-2 rounded-full bg-blue-100 text-blue-600 mr-2">
                                            <ShoppingBag className="w-4 h-4"/>
                                        </button>
                                        {product.stockQuantity} items left
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                )}
            </div>
        </>
    )
}
export default CardPopularProducts;
