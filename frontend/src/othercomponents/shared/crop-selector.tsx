"use client"

import { useState, useEffect } from "react"
import { Search } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { ScrollArea } from "@/components/ui/scroll-area"
import {Crop} from "@/lib/types/crop";
import {useCropContext} from "@/context/CropContext";

interface CropSelectorProps {
    onCropSelect: (crop: Crop) => void
}

export function CropSelector({onCropSelect}: CropSelectorProps) {
    const { crops, getAllCrops } = useCropContext()
    const [isOpen, setIsOpen] = useState(false)
    const [searchTerm, setSearchTerm] = useState("")
    const [selectedCrop, setSelectedCrop] = useState<Crop | null>(null)
    const [filteredCrops, setFilteredCrops] = useState(crops)

    useEffect(() => {
        getAllCrops()
    }, [getAllCrops])

    useEffect(() => {
        const filtered = crops.filter((crop) => crop.name.toLowerCase().includes(searchTerm.toLowerCase()))
        setFilteredCrops(filtered)
    }, [searchTerm])

    const handleCropSelect = (crop: Crop) => {
        setSelectedCrop(crop)
        setIsOpen(false)
    }

    return (
        <Dialog open={isOpen} onOpenChange={setIsOpen}>
            <DialogTrigger asChild>
                <Button variant="outline" className="w-full justify-between">
                    {selectedCrop ? selectedCrop.name : "Select crop..."}
                    <Search className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Select a Crop</DialogTitle>
                </DialogHeader>
                <div className="grid gap-4 py-4">
                    <Input
                        placeholder="Search crops..."
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        className="col-span-3"
                    />
                    <ScrollArea className="h-[200px]">
                        {filteredCrops.length === 0 ? (
                            <p className="text-sm text-muted-foreground p-2">
                                {crops.length === 0 ? "No crops available. Please add crops first." : "No crop found. Type to search."}
                            </p>
                        ) : (
                            filteredCrops.map((crop) => (
                                <Button
                                    key={crop.id}
                                    variant="ghost"
                                    className="w-full justify-start font-normal"
                                    onClick={() => handleCropSelect(crop)}
                                >
                                    <span className="truncate">{crop.name}</span>
                                </Button>
                            ))
                        )}
                    </ScrollArea>
                </div>
            </DialogContent>
        </Dialog>
    )
}