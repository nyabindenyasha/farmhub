import React, {createContext, useContext, useState, ReactNode, useCallback} from "react";
import apiClient from "../utils/apiClient";
import {CropStagesOfGrowth} from "@/lib/types/crop-stages-of-growth";
import {CropStagesOfGrowthRequest} from "@/othercomponents/cropstages/create-crop-stages-of-growth";

// Define the context type
interface CropStagesOfGrowthContextType {
    cropStagesOfGrowths: CropStagesOfGrowth[];
    getAllCropStagesOfGrowths: () => Promise<void>;
    createCropStagesOfGrowth: (cropStagesOfGrowthData: CropStagesOfGrowthRequest) => Promise<void>;
}

// Create the context with a default value
export const CropStagesOfGrowthContext = createContext<CropStagesOfGrowthContextType | undefined>(undefined);

// Create the provider component
interface CropStagesOfGrowthProviderProps {
    children: ReactNode;
}

export const CropStagesOfGrowthProvider: React.FC<CropStagesOfGrowthProviderProps> = ({children}) => {
    const [cropStagesOfGrowths, setCropStagesOfGrowths] = useState<CropStagesOfGrowth[]>([]);

    const getAllCropStagesOfGrowths = useCallback(async (): Promise<void> => {
        try {
            const response = await apiClient.get<CropStagesOfGrowth[]>("/v1/api/crop-stages-of-growth");
            setCropStagesOfGrowths(response.data);
        } catch (error) {
            console.error("Error fetching cropStagesOfGrowths:", error);
        }
    }, []);

    const createCropStagesOfGrowth = async (cropStagesOfGrowthData: CropStagesOfGrowthRequest): Promise<void> => {
        try {
            const response = await apiClient.post<CropStagesOfGrowth>("/v1/api/crop-stages-of-growth", cropStagesOfGrowthData, {
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