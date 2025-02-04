import React, {createContext, useContext, useState, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {CropVariety} from "@/lib/types/crop-variety";
import {BASE_URL} from "@/lib/constants";
import {CropVarietyRequest} from "@/othercomponents/crop/create-crop-variety";

// Define the context type
interface CropVarietyContextType {
    cropVarieties: CropVariety[];
    getAllCropVarieties: () => Promise<void>;
    createCropVariety: (cropVarietyData: CropVarietyRequest) => Promise<void>;
}

// Create the context with a default value
export const CropVarietyContext = createContext<CropVarietyContextType | undefined>(undefined);

// Create the provider component
interface CropVarietyProviderProps {
    children: ReactNode;
}

export const CropVarietyProvider: React.FC<CropVarietyProviderProps> = ({children}) => {
    const [cropVarieties, setCropVarieties] = useState<CropVariety[]>([]);

    const getAllCropVarieties = async (): Promise<void> => {
        console.log(BASE_URL + "/v1/api/cropVariety")
        try {
            const response = await apiClient.get<CropVariety[]>(BASE_URL + "/v1/api/crop-variety");
            setCropVarieties(response.data);
        } catch (error) {
            console.error("Error fetching cropVarieties:", error);
        }
    };

    const createCropVariety = async (cropVarietyData: CropVarietyRequest): Promise<void> => {
        try {
            const response = await apiClient.post<CropVariety>(BASE_URL + "/v1/api/crop-variety", cropVarietyData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setCropVarieties([...cropVarieties, response.data]);
        } catch (error) {
            console.error("Error creating cropVariety:", error);
        }
    };

    return (
        <CropVarietyContext.Provider value={{cropVarieties, getAllCropVarieties, createCropVariety}}>
            {children}
        </CropVarietyContext.Provider>
    );
};

// Custom hook to use the CropVarietyContext
export const useCropVarietyContext = (): CropVarietyContextType => {
    const context = useContext(CropVarietyContext);
    if (!context) {
        throw new Error("useCropVarietyContext must be used within a CropVarietyProvider");
    }
    return context;
};