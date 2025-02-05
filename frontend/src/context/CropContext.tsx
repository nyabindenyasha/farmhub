import React, {createContext, useContext, useState, ReactNode, useCallback} from "react";
import apiClient from "../utils/apiClient";
import {Crop} from "@/lib/types/crop";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface CropContextType {
    crops: Crop[];
    getAllCrops: () => Promise<void>;
    createCrop: (cropData: Crop) => Promise<void>;
}

// Create the context with a default value
export const CropContext = createContext<CropContextType | undefined>(undefined);

// Create the provider component
interface CropProviderProps {
    children: ReactNode;
}

export const CropProvider: React.FC<CropProviderProps> = ({children}) => {
    const [crops, setCrops] = useState<Crop[]>([]);

    const getAllCrops = useCallback(async (): Promise<void> => {
        try {
            const response = await apiClient.get<Crop[]>("/v1/api/crop");
            setCrops(response.data);

        } catch (error) {

            console.error("Error fetching crops:", error);
        }
    }, []);

    const createCrop = async (cropData: Crop): Promise<void> => {
        try {
            const response = await apiClient.post<Crop>("/v1/api/crop", cropData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setCrops([...crops, response.data]);
        } catch (error) {
            console.error("Error creating crop:", error);
        }
    };

    return (
        <CropContext.Provider value={{crops, getAllCrops, createCrop}}>
            {children}
        </CropContext.Provider>
    );
};

// Custom hook to use the CropContext
export const useCropContext = (): CropContextType => {
    const context = useContext(CropContext);
    if (!context) {
        throw new Error("useCropContext must be used within a CropProvider");
    }
    return context;
};
