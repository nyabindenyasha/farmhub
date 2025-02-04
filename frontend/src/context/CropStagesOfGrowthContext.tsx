import React, {createContext, useContext, useState, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {CropStagesOfGrowth} from "@/lib/types/crop-stages-of-growth";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface CropStagesOfGrowthContextType {
    cropStagesOfGrowths: CropStagesOfGrowth[];
    getAllCropStagesOfGrowths: () => Promise<void>;
    createCropStagesOfGrowth: (cropStagesOfGrowthData: CropStagesOfGrowth) => Promise<void>;
}

// Create the context with a default value
export const CropStagesOfGrowthContext = createContext<CropStagesOfGrowthContextType | undefined>(undefined);

// Create the provider component
interface CropStagesOfGrowthProviderProps {
    children: ReactNode;
}

export const CropStagesOfGrowthProvider: React.FC<CropStagesOfGrowthProviderProps> = ({children}) => {
    const [cropStagesOfGrowths, setCropStagesOfGrowths] = useState<CropStagesOfGrowth[]>([]);

    const getAllCropStagesOfGrowths = async (): Promise<void> => {
        console.log(BASE_URL + "/v1/api/cropStagesOfGrowth")
        try {
            const response = await apiClient.get<CropStagesOfGrowth[]>(BASE_URL + "/v1/api/cropStagesOfGrowth");
            setCropStagesOfGrowths(response.data);
        } catch (error) {
            console.error("Error fetching cropStagesOfGrowths:", error);
        }
    };

    const createCropStagesOfGrowth = async (cropStagesOfGrowthData: CropStagesOfGrowth): Promise<void> => {
        try {
            const response = await apiClient.post<CropStagesOfGrowth>(BASE_URL + "/v1/api/cropStagesOfGrowth", cropStagesOfGrowthData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setCropStagesOfGrowths([...cropStagesOfGrowths, response.data]);
        } catch (error) {
            console.error("Error creating cropStagesOfGrowth:", error);
        }
    };

    return (
        <CropStagesOfGrowthContext.Provider
            value={{cropStagesOfGrowths, getAllCropStagesOfGrowths, createCropStagesOfGrowth}}>
            {children}
        </CropStagesOfGrowthContext.Provider>
    );
};

// Custom hook to use the CropStagesOfGrowthContext
export const useCropStagesOfGrowthContext = (): CropStagesOfGrowthContextType => {
    const context = useContext(CropStagesOfGrowthContext);
    if (!context) {
        throw new Error("useCropStagesOfGrowthContext must be used within a CropStagesOfGrowthProvider");
    }
    return context;
};