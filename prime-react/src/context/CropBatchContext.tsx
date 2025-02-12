import React, {createContext, useContext, useState, useCallback, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {CropBatch} from "@/lib/types/crop-batch";
import {CropBatchRequest} from "@/farmercomponents/cropbatches/create-crop-batch";


// Define the context type
interface CropBatchContextType {
    cropBatches: CropBatch[];
    getAllCropBatches: () => Promise<void>;
    // cropBatch: CropBatch;
    getCropBatchById: (id: number) => Promise<CropBatch | null>
    createCropBatch: (cropBatchData: CropBatchRequest) => Promise<void>;
}

// Create the context with a default value
export const CropBatchContext = createContext<CropBatchContextType | undefined>(undefined);

// Create the provider component
interface CropBatchProviderProps {
    children: ReactNode;
}

export const CropBatchProvider: React.FC<CropBatchProviderProps> = ({children}) => {
    const [cropBatches, setCropBatches] = useState<CropBatch[]>([]);

    const getAllCropBatches = useCallback(async (): Promise<void> => {
        try {
            const response = await apiClient.get<CropBatch[]>("/v1/api/crop/batch");
            setCropBatches(response.data);
        } catch (error) {
            console.error("Error fetching Crop Batches:", error);
        }
    }, []);

    const getCropBatchById = useCallback(async (id: number): Promise<CropBatch | null> => {
        try {
            const response = await apiClient.get<CropBatch>("/v1/api/crop/batch/" + id);
            return response.data;
        } catch (error) {
            console.error("Error fetching users:", error);
            return null;
        }
    }, []);

    const createCropBatch = useCallback(async (cropBatchData: CropBatchRequest): Promise<void> => {
        try {
            const response = await apiClient.post<CropBatch>("/v1/api/crop/batch", cropBatchData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setCropBatches(prevBatches => [...prevBatches, response.data]);
        } catch (error) {
            console.error("Error creating cropBatch:", error);
        }
    }, []);

    return (
        <CropBatchContext.Provider value={{cropBatches, getAllCropBatches, getCropBatchById, createCropBatch}}>
            {children}
        </CropBatchContext.Provider>
    );
};

// Custom hook to use the CropBatchContext
export const useCropBatchContext = (): CropBatchContextType => {
    const context = useContext(CropBatchContext);
    if (!context) {
        throw new Error("useCropBatchContext must be used within a CropBatchProvider");
    }
    return context;
};
