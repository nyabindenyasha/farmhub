import React, {createContext, useContext, useState, ReactNode, useCallback} from "react";
import apiClient from "../utils/apiClient";
import {Fertilizer} from "@/lib/types/fertilizer";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface FertilizerContextType {
    fertilizers: Fertilizer[];
    getAllFertilizers: () => Promise<void>;
    createFertilizer: (fertilizerData: Fertilizer) => Promise<void>;
}

// Create the context with a default value
export const FertilizerContext = createContext<FertilizerContextType | undefined>(undefined);

// Create the provider component
interface FertilizerProviderProps {
    children: ReactNode;
}

export const FertilizerProvider: React.FC<FertilizerProviderProps> = ({children}) => {
    const [fertilizers, setFertilizers] = useState<Fertilizer[]>([]);

    const getAllFertilizers = useCallback(async (): Promise<void> => {
            console.log(BASE_URL + "/v1/api/fertilizer")
            try {
                const response = await apiClient.get<Fertilizer[]>(BASE_URL + "/v1/api/fertilizer");
                setFertilizers(response.data);
            } catch (error) {
                console.error("Error fetching fertilizers:", error);
            }
        }, []
    );

    const createFertilizer = async (fertilizerData: Fertilizer): Promise<void> => {
        try {
            const response = await apiClient.post<Fertilizer>(BASE_URL + "/v1/api/fertilizer", fertilizerData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setFertilizers([...fertilizers, response.data]);
        } catch (error) {
            console.error("Error creating fertilizer:", error);
        }
    };

    return (
        <FertilizerContext.Provider value={{fertilizers, getAllFertilizers, createFertilizer}}>
            {children}
        </FertilizerContext.Provider>
    );
};

// Custom hook to use the FertilizerContext
export const useFertilizerContext = (): FertilizerContextType => {
    const context = useContext(FertilizerContext);
    if (!context) {
        throw new Error("useFertilizerContext must be used within a FertilizerProvider");
    }
    return context;
};