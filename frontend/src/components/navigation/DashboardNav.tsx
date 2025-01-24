import React from "react";
import {UserCircleIcon} from "@heroicons/react/24/outline";
import UserDropDown from "@/components/userdropdown/UserDropDown";
import {Search} from "lucide-react";
import { Input } from "@/components/ui/input"

function DashboardNav(){
    return(
            <header className="bg-white border-b border-gray-200 p-4">
                <div className="flex items-center justify-between">
                    <div className="relative w-96">
                        <Search className="absolute left-2 top-2.5 h-4 w-4 text-gray-400"/>
                        <Input placeholder="Search here..." className="pl-8 bg-[#f8f9fa] border-0"/>
                    </div>
                    <div className="flex items-center gap-2">
                        <UserDropDown/>
                        <div>
                            <div className="font-medium">Kudzai Damba</div>
                            <div className="text-xs text-gray-500">Admin</div>
                        </div>
                    </div>
                </div>
            </header>
    )
}
export default DashboardNav
