import React, {createContext, useContext, useState, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {CropProgram} from "@/lib/types/crop-program";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface CropProgramContextType {
    cropPrograms: CropProgram[];
    getAllCropPrograms: () => Promise<void>;
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

    const getAllCropPrograms = async (): Promise<void> => {
        console.log("### Get all CropPrograms")
        console.log(BASE_URL + "/v1/api/crop-program")
        try {
            const response = await apiClient.get<CropProgram[]>(BASE_URL + "/v1/api/crop-program");
            setCropPrograms(response.data);
        } catch (error) {
            console.error("Error fetching cropPrograms:", error);
        }
    };

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

    return (
        <CropProgramContext.Provider value={{cropPrograms, getAllCropPrograms, createCropProgram}}>
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