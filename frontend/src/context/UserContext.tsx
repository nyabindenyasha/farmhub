import React, {createContext, useContext, useState, ReactNode} from "react";
import apiClient from "../utils/apiClient";
import {User} from "@/lib/types";
import id from "@/pages/dashboard/patient/[id]";

// Define the context type
interface UserContextType {
    user: User | null;
    setUser: (user: User | null) => void;
    logout: () => void;
    getAllUsers: () => Promise<User[]>;
    getUserById: (id: number) => Promise<User | null>;
}

// Create the context with a default value
export const UserContext = createContext<UserContextType | undefined>(undefined);

// Create the provider component
interface UserProviderProps {
    children: ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({children}) => {
    const [user, setUser] = useState<User | null>(null);

    const logout = () => {
        setUser(null);
        localStorage.removeItem("token");
    };

    const getAllUsers = async (): Promise<User[]> => {
        try {
            const response = await apiClient.get<User[]>("/v1/api/policy-member/all");
            return response.data;
        } catch (error) {
            console.error("Error fetching users:", error);
            return [];
        }
    };

    const getUserById = async (id: number): Promise<User | null> => {
        try {
            const response = await apiClient.get<User>("/v1/api/policy-member/" + id);
            return response.data;
        } catch (error) {
            console.error("Error fetching users:", error);
            return null
        }
    };

    return (
        <UserContext.Provider value={{user, setUser, logout, getAllUsers, getUserById}}>
            {children}
        </UserContext.Provider>
    );
};

