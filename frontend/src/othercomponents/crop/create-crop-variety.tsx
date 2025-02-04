import type React from "react"
import { useState, useEffect } from "react"
import { useCropContext } from "@/context/CropContext"
import { useCropVarietyContext } from "@/context/CropVarietyContext"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import { Card, CardContent, CardHeader, CardTitle, CardDescription, CardFooter } from "@/components/ui/card"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import { Loader2, CheckCircle, XCircle, Search } from "lucide-react"
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from "@/components/ui/command";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import {Crop} from "@/lib/types/crop";

export interface CropVarietyRequest {
    cropId: number
    variety: string
    maturityStartDay: number
    maturityEndDay: number
    harvestDuration: number
    remarks: string
}

export default function CreateCropVariety() {
    const { crops, getAllCrops } = useCropContext()
    const { createCropVariety } = useCropVarietyContext()
    const [searchTerm, setSearchTerm] = useState("")
    const [selectedCrop, setSelectedCrop] = useState<Crop | null>(null)
    const [cropVariety, setCropVariety] = useState<CropVarietyRequest>({
        cropId: 0,
        variety: "",
        maturityStartDay: 0,
        maturityEndDay: 0,
        harvestDuration: 0,
        remarks: "",
    })
    const [isLoading, setIsLoading] = useState(false)
    const [submitStatus, setSubmitStatus] = useState<"idle" | "success" | "error">("idle")
    const [isSearchOpen, setIsSearchOpen] = useState(false)

    useEffect(() => {
        getAllCrops()
    }, [getAllCrops])

    const filteredCrops = crops.filter((crop) => crop.name.toLowerCase().includes(searchTerm.toLowerCase()))

    const handleCropSelect = (crop: Crop) => {
        setSelectedCrop(crop)
        setCropVariety((prev) => ({ ...prev, cropId: crop.id }))
        setIsSearchOpen(false)
    }

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target
        setCropVariety((prev) => ({
            ...prev,
            [name]: name.includes("Day") || name === "harvestDuration" ? Number.parseInt(value) : value,
        }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setIsLoading(true)
        try {
            await createCropVariety(cropVariety)
            setSubmitStatus("success")
        } catch (error) {
            console.error("Error creating crop variety:", error)
            setSubmitStatus("error")
        } finally {
            setIsLoading(false)
        }
    }

    const resetForm = () => {
        setSelectedCrop(null)
        setCropVariety({
            cropId: 0,
            variety: "",
            maturityStartDay: 0,
            maturityEndDay: 0,
            harvestDuration: 0,
            remarks: "",
        })
        setSubmitStatus("idle")
    }

    return (
        <Card className="w-full max-w-2xl mx-auto">
            <CardHeader>
                <CardTitle>Create Crop Variety</CardTitle>
                <CardDescription>Search for a crop, then add a new variety</CardDescription>
            </CardHeader>
            <CardContent>
                <div className="space-y-4">
                    <div>
                        <Label htmlFor="cropSearch">Search Crop</Label>
                        <Popover open={isSearchOpen} onOpenChange={setIsSearchOpen}>
                            <PopoverTrigger asChild>
                                <Button
                                    variant="outline"
                                    role="combobox"
                                    aria-expanded={isSearchOpen}
                                    className="w-full justify-between"
                                >
                                    {selectedCrop ? selectedCrop.name : "Select crop..."}
                                    <Search className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent className="w-[300px] p-0">
                                <Command>
                                    <CommandInput placeholder="Search crops..." onValueChange={setSearchTerm} />
                                    <CommandEmpty>No crop found.</CommandEmpty>
                                    <CommandGroup>
                                        <CommandList>
                                            {filteredCrops.map((crop) => (
                                                <CommandItem key={crop.id} onSelect={() => handleCropSelect(crop)}>
                                                    {crop.name}
                                                </CommandItem>
                                            ))}
                                        </CommandList>
                                    </CommandGroup>
                                </Command>
                            </PopoverContent>
                        </Popover>
                    </div>

                    {selectedCrop && (
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <div>
                                <Label htmlFor="variety">Variety Name</Label>
                                <Input id="variety" name="variety" value={cropVariety.variety} onChange={handleInputChange} required />
                            </div>
                            <div>
                                <Label htmlFor="maturityStartDay">Maturity Start Day</Label>
                                <Input
                                    id="maturityStartDay"
                                    name="maturityStartDay"
                                    type="number"
                                    value={cropVariety.maturityStartDay}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>
                            <div>
                                <Label htmlFor="maturityEndDay">Maturity End Day</Label>
                                <Input
                                    id="maturityEndDay"
                                    name="maturityEndDay"
                                    type="number"
                                    value={cropVariety.maturityEndDay}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>
                            <div>
                                <Label htmlFor="harvestDuration">Harvest Duration (days)</Label>
                                <Input
                                    id="harvestDuration"
                                    name="harvestDuration"
                                    type="number"
                                    value={cropVariety.harvestDuration}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>
                            <div>
                                <Label htmlFor="remarks">Remarks</Label>
                                <Textarea id="remarks" name="remarks" value={cropVariety.remarks} onChange={handleInputChange} />
                            </div>
                            <Button type="submit" disabled={isLoading}>
                                {isLoading ? (
                                    <>
                                        <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                                        Submitting...
                                    </>
                                ) : (
                                    "Create Crop Variety"
                                )}
                            </Button>
                        </form>
                    )}
                </div>
            </CardContent>
            <CardFooter>
                {submitStatus === "success" && (
                    <Alert>
                        <CheckCircle className="h-4 w-4" />
                        <AlertTitle>Success</AlertTitle>
                        <AlertDescription>Crop variety has been successfully created.</AlertDescription>
                    </Alert>
                )}
                {submitStatus === "error" && (
                    <Alert variant="destructive">
                        <XCircle className="h-4 w-4" />
                        <AlertTitle>Error</AlertTitle>
                        <AlertDescription>There was an error creating the crop variety. Please try again.</AlertDescription>
                    </Alert>
                )}
                {submitStatus !== "idle" && (
                    <Button onClick={resetForm} className="mt-4">
                        {submitStatus === "success" ? "Create Another Variety" : "Try Again"}
                    </Button>
                )}
            </CardFooter>
        </Card>
    )
}

