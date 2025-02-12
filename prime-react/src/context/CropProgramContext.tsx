import React, {createContext, useContext, useState, ReactNode, useCallback} from "react";
import apiClient from "../utils/apiClient";
import {CropProgram} from "@/lib/types/crop-program";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface CropProgramContextType {
    cropPrograms: CropProgram[];
    getAllCropPrograms: () => Promise<void>;
    getCropProgramsByCrop: (cropId: number) => Promise<void>
    getCropProgramById: (id: number) => Promise<CropProgram | null>
    createCropProgram: (cropProgramData: CropProgram) => Promise<void>;
}

// Create the context with a default value
export const CropProgramContext = createContext<CropProgramContextType | undefined>(undefined);

// Create the provider component
interface CropProgramProviderProps {
    children: ReactNode;
}

export const CropProgramProvider: React.FC<CropProgramProviderProps> = ({children}) => {
    const [cropPrograms, setCropPrograms] = useState<CropProgram[]>([]);

    const getAllCropPrograms = useCallback(async (): Promise<void> => {
        try {
            const response = await apiClient.get<CropProgram[]>(BASE_URL + "/v1/api/crop-program");
            setCropPrograms(response.data);
        } catch (error) {
            console.error("Error fetching cropPrograms:", error);
        }
    }, [])

    const getCropProgramsByCrop = useCallback(async (cropId: number): Promise<void> => {
        try {
            const response = await apiClient.get<CropProgram[]>(BASE_URL + "/v1/api/crop-program/crop/" + cropId);
            setCropPrograms(response.data);
        } catch (error) {
            console.error("Error fetching cropPrograms:", error);
        }
    }, [])

    const createCropProgram = async (cropProgramData: CropProgram): Promise<void> => {
        try {
            const response = await apiClient.post<CropProgram>(BASE_URL + "/v1/api/crop-program", cropProgramData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setCropPrograms([...cropPrograms, response.data]);
        } catch (error) {
            console.error("Error creating Crop Program:", error);
        }
    };

    const getCropProgramById = useCallback(async (id: number): Promise<CropProgram | null> => {
        try {
            const response = await apiClient.get<CropProgram>("/v1/api/crop-program/" + id);
            return response.data;
        } catch (error) {
            console.error("Error fetching users:", error);
            return null;
        }
    }, []);

    return (
        <CropProgramContext.Provider value={{cropPrograms, getAllCropPrograms, getCropProgramsByCrop, getCropProgramById, createCropProgram}}>
            {children}
        </CropProgramContext.Provider>
    );
};

// Custom hook to use the CropProgramContext
export const useCropProgramContext = (): CropProgramContextType => {
    const context = useContext(CropProgramContext);
    if (!context) {
        throw new Error("useCropProgramContext must be used within a CropProgramProvider");
    }
    return context;
};