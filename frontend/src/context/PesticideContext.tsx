import React, {createContext, useContext, useState, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {Pesticide} from "@/lib/types/pesticide";
import {BASE_URL} from "@/lib/constants";

// Define the context type
interface PesticideContextType {
    pesticides: Pesticide[];
    getAllPesticides: () => Promise<void>;
    createPesticide: (pesticideData: Pesticide) => Promise<void>;
}

// Create the context with a default value
export const PesticideContext = createContext<PesticideContextType | undefined>(undefined);

// Create the provider component
interface PesticideProviderProps {
    children: ReactNode;
}

export const PesticideProvider: React.FC<PesticideProviderProps> = ({children}) => {
    const [pesticides, setPesticides] = useState<Pesticide[]>([]);

    const getAllPesticides = async (): Promise<void> => {
        console.log(BASE_URL + "/v1/api/pesticide")
        try {
            const response = await apiClient.get<Pesticide[]>(BASE_URL + "/v1/api/pesticide");
            setPesticides(response.data);
        } catch (error) {
            console.error("Error fetching pesticides:", error);
        }
    };

    const createPesticide = async (pesticideData: Pesticide): Promise<void> => {
        try {
            const response = await apiClient.post<Pesticide>(BASE_URL + "/v1/api/pesticide", pesticideData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setPesticides([...pesticides, response.data]);
        } catch (error) {
            console.error("Error creating pesticide:", error);
        }
    };

    return (
        <PesticideContext.Provider value={{pesticides, getAllPesticides, createPesticide}}>
            {children}
        </PesticideContext.Provider>
    );
};

// Custom hook to use the PesticideContext
export const usePesticideContext = (): PesticideContextType => {
    const context = useContext(PesticideContext);
    if (!context) {
        throw new Error("usePesticideContext must be used within a PesticideProvider");
    }
    return context;
};