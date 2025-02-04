import type React from "react"
import {useEffect, useState} from "react"
import {Button} from "@/components/ui/button"
import {Input} from "@/components/ui/input"
import {Label} from "@/components/ui/label"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select"
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card"
import {Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle} from "@/components/ui/dialog"
import {Alert, AlertDescription, AlertTitle} from "@/components/ui/alert"
import {CheckCircle, Loader2, Maximize2, Minimize2, Plus, Search, Trash2, XCircle} from "lucide-react"
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from "@/components/ui/command"
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import {Period} from "@/lib/types/period";
import {StagesOfGrowth} from "@/lib/enums/stages-of-growth";
import {PeriodUnit} from "@/lib/enums/period-unit";
import {useCropContext} from "@/context/CropContext";
import {Crop} from "@/lib/types/crop";
import {FormProps} from "@/lib/types";

interface GrowthStage {
    stageStartDate: Period
    stageEndDate: Period
    stageOfGrowth: StagesOfGrowth
    expanded: boolean
}

interface CropStagesOfGrowth {
    cropId: number
    stagesOfGrowth: GrowthStage[]
}

const initialCropStagesOfGrowth: CropStagesOfGrowth = {
    cropId: 0,
    stagesOfGrowth: [
        {
            stageStartDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
            stageEndDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
            stageOfGrowth: StagesOfGrowth.TRANSPLANTING,
            expanded: true,
        },
    ],
}

export default function CreateCropStagesOfGrowth({isOpen, onClose}: FormProps) {
    const [cropStagesOfGrowth, setCropStagesOfGrowth] = useState<CropStagesOfGrowth>(initialCropStagesOfGrowth)
    const [isDialogOpen, setIsDialogOpen] = useState(false)
    const [isLoading, setIsLoading] = useState(false)
    const [submitStatus, setSubmitStatus] = useState<"idle" | "success" | "error">("idle")
    const [showSummary, setShowSummary] = useState(false)

    const handleCropIdChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCropStagesOfGrowth((prev) => ({...prev, cropId: Number(e.target.value)}))
    }

    const handleStageChange = (index: number, field: keyof GrowthStage, value: any) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) => (i === index ? {...stage, [field]: value} : stage)),
        }))
    }

    const handlePeriodChange = (
        index: number,
        dateType: "stageStartDate" | "stageEndDate",
        field: keyof Period,
        value: any,
    ) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) =>
                i === index
                    ? {
                        ...stage,
                        [dateType]: {...stage[dateType], [field]: field === "periodValue" ? Number(value) : value},
                    }
                    : stage,
            ),
        }))
    }

    const addStage = () => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            stagesOfGrowth: [
                ...prev.stagesOfGrowth,
                {
                    stageStartDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
                    stageEndDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
                    stageOfGrowth: StagesOfGrowth.TRANSPLANTING,
                    expanded: true,
                },
            ],
        }))
    }

    const removeStage = (index: number) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            stagesOfGrowth: prev.stagesOfGrowth.filter((_, i) => i !== index),
        }))
    }

    const toggleExpanded = (index: number) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) =>
                i === index ? {...stage, expanded: !stage.expanded} : stage,
            ),
        }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setShowSummary(true)
    }

    const confirmSubmit = async () => {
        setIsLoading(true)
        setIsDialogOpen(false)
        setShowSummary(false)

        try {
            // Simulate API call
            await new Promise((resolve) => setTimeout(resolve, 2000))

            // Here you would typically send the data to your API
            // const response = await fetch('/api/crop-stages-of-growth', {
            //   method: 'POST',
            //   headers: { 'Content-Type': 'application/json' },
            //   body: JSON.stringify(cropStagesOfGrowth)
            // })
            // if (!response.ok) throw new Error('Failed to submit crop stages of growth')

            console.log("Submitting crop stages of growth:", cropStagesOfGrowth)
            setSubmitStatus("success")
        } catch (error) {
            console.error("Error submitting crop stages of growth:", error)
            setSubmitStatus("error")
        } finally {
            setIsLoading(false)
        }
    }

    const resetForm = () => {
        setCropStagesOfGrowth(initialCropStagesOfGrowth)
        setSubmitStatus("idle")
        setShowSummary(false)
    }

    const openDialog = () => setIsDialogOpen(true)

    const renderSummary = () => (
        <div className="space-y-6">
            <h3 className="text-lg font-semibold">Summary</h3>
            <div>
                <p className="font-medium">Crop ID: {cropStagesOfGrowth.cropId}</p>
            </div>
            {cropStagesOfGrowth.stagesOfGrowth.map((stage, index) => (
                <Card key={index}>
                    <CardHeader>
                        <CardTitle>
                            Stage {index + 1}: {stage.stageOfGrowth}
                        </CardTitle>
                    </CardHeader>
                    <CardContent>
                        <p>
                            Start: {stage.stageStartDate.periodValue} {stage.stageStartDate.periodUnit}
                        </p>
                        <p>
                            End: {stage.stageEndDate.periodValue} {stage.stageEndDate.periodUnit}
                        </p>
                    </CardContent>
                </Card>
            ))}
            <div className="flex justify-between">
                <Button onClick={() => setShowSummary(false)} variant="outline">
                    Edit
                </Button>
                <Button onClick={confirmSubmit} disabled={isLoading}>
                    {isLoading ? (
                        <>
                            <Loader2 className="mr-2 h-4 w-4 animate-spin"/>
                            Submitting...
                        </>
                    ) : (
                        "Confirm & Submit"
                    )}
                </Button>
            </div>
        </div>
    )

    let periodUnits = Object.keys(PeriodUnit);
    let stagesOfGrowth = Object.keys(StagesOfGrowth);

    return (
        <div className="max-w-4xl mx-auto p-4">
            <Card>
                <CardHeader>
                    <CardTitle>Create Crop Stages of Growth</CardTitle>
                    <CardDescription>Define the stages of growth for a crop</CardDescription>
                </CardHeader>
                <CardContent>
                    {submitStatus === "idle" && (
                        <div className="flex justify-between items-center">
                            <div>Define stages of growth</div>
                            <Button onClick={openDialog} disabled={isLoading}>
                                Start
                            </Button>
                        </div>
                    )}
                    {submitStatus === "success" && (
                        <Alert>
                            <CheckCircle className="h-4 w-4"/>
                            <AlertTitle>Success</AlertTitle>
                            <AlertDescription>Your crop stages of growth have been successfully
                                submitted.</AlertDescription>
                        </Alert>
                    )}
                    {submitStatus === "error" && (
                        <Alert variant="destructive">
                            <XCircle className="h-4 w-4"/>
                            <AlertTitle>Error</AlertTitle>
                            <AlertDescription>
                                There was an error submitting your crop stages of growth. Please try again.
                            </AlertDescription>
                        </Alert>
                    )}
                </CardContent>
                {submitStatus !== "idle" && (
                    <CardFooter>
                        <Button
                            onClick={resetForm}>{submitStatus === "success" ? "Create Another" : "Try Again"}</Button>
                    </CardFooter>
                )}
            </Card>

            <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
                <DialogContent className="max-w-4xl max-h-[80vh] overflow-y-auto">
                    <DialogHeader>
                        <DialogTitle>Create Crop Stages of Growth</DialogTitle>
                        <DialogDescription>Define the stages of growth for your crop</DialogDescription>
                    </DialogHeader>
                    {showSummary ? (
                        renderSummary()
                    ) : (
                        <form onSubmit={handleSubmit} className="space-y-6">
                            <div>
                                <Label htmlFor="cropId">Crop ID</Label>
                                <Input
                                    id="cropId"
                                    type="number"
                                    value={cropStagesOfGrowth.cropId}
                                    onChange={handleCropIdChange}
                                    required
                                />
                            </div>

                            {cropStagesOfGrowth.stagesOfGrowth.map((stage, index) => (
                                <Card key={index}>
                                    <CardHeader>
                                        <div className="flex justify-between items-center">
                                            <CardTitle className="text-lg">Stage {index + 1}</CardTitle>
                                            <Button variant="ghost" size="sm" onClick={() => toggleExpanded(index)}>
                                                {stage.expanded ? <Minimize2 className="h-4 w-4"/> :
                                                    <Maximize2 className="h-4 w-4"/>}
                                            </Button>
                                        </div>
                                    </CardHeader>
                                    <CardContent className="space-y-4">
                                        {!stage.expanded && (
                                            <div className="text-sm text-gray-500">
                                                {stage.stageOfGrowth} ({stage.stageStartDate.periodValue} {stage.stageStartDate.periodUnit} -{" "}
                                                {stage.stageEndDate.periodValue} {stage.stageEndDate.periodUnit})
                                            </div>
                                        )}
                                        {stage.expanded && (
                                            <>
                                                <div>
                                                    <Label>Stage of Growth</Label>
                                                    <Select
                                                        value={stage.stageOfGrowth.toString()}
                                                        onValueChange={(value) => handleStageChange(index, "stageOfGrowth", value)}
                                                    >
                                                        <SelectTrigger>
                                                            <SelectValue placeholder="Select stage of growth"/>
                                                        </SelectTrigger>
                                                        <SelectContent>
                                                            {stagesOfGrowth.map((stageOfGrowth) => (
                                                                <SelectItem value={stageOfGrowth}>
                                                                    {stageOfGrowth}
                                                                </SelectItem>
                                                            ))}
                                                        </SelectContent>
                                                    </Select>
                                                </div>
                                                <div className="grid grid-cols-2 gap-4">
                                                    <div>
                                                        <Label>Start Date</Label>
                                                        <div className="flex space-x-2">
                                                            <Input
                                                                type="number"
                                                                value={stage.stageStartDate.periodValue}
                                                                onChange={(e) =>
                                                                    handlePeriodChange(index, "stageStartDate", "periodValue", e.target.value)
                                                                }
                                                                required
                                                            />
                                                            <Select
                                                                value={stage.stageStartDate.periodUnit.toString()}
                                                                onValueChange={(value) =>
                                                                    handlePeriodChange(index, "stageStartDate", "periodUnit", value)
                                                                }
                                                            >
                                                                <SelectTrigger>
                                                                    <SelectValue placeholder="Unit"/>
                                                                </SelectTrigger>
                                                                <SelectContent>
                                                                    {periodUnits.map((periodUnit) => (
                                                                        <SelectItem value={periodUnit}>
                                                                            {periodUnit}
                                                                        </SelectItem>
                                                                    ))}
                                                                </SelectContent>
                                                            </Select>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <Label>End Date</Label>
                                                        <div className="flex space-x-2">
                                                            <Input
                                                                type="number"
                                                                value={stage.stageEndDate.periodValue}
                                                                onChange={(e) =>
                                                                    handlePeriodChange(index, "stageEndDate", "periodValue", e.target.value)
                                                                }
                                                                required
                                                            />
                                                            <Select
                                                                value={stage.stageEndDate.periodUnit.toString()}
                                                                onValueChange={(value) =>
                                                                    handlePeriodChange(index, "stageEndDate", "periodUnit", value)
                                                                }
                                                            >
                                                                <SelectTrigger>
                                                                    <SelectValue placeholder="Unit"/>
                                                                </SelectTrigger>
                                                                <SelectContent>
                                                                    {periodUnits.map((periodUnit) => (
                                                                        <SelectItem value={periodUnit}>
                                                                            {periodUnit}
                                                                        </SelectItem>
                                                                    ))}
                                                                </SelectContent>
                                                            </Select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </>
                                        )}
                                    </CardContent>
                                    <CardFooter>
                                        <Button
                                            type="button"
                                            variant="destructive"
                                            onClick={() => removeStage(index)}
                                            disabled={cropStagesOfGrowth.stagesOfGrowth.length === 1}
                                        >
                                            <Trash2 className="w-4 h-4 mr-2"/>
                                            Remove Stage
                                        </Button>
                                    </CardFooter>
                                </Card>
                            ))}

                            <Button type="button" onClick={addStage} className="w-full">
                                <Plus className="w-4 h-4 mr-2"/>
                                Add Stage
                            </Button>

                            <div className="flex justify-between">
                                <Button type="button" onClick={() => setIsDialogOpen(false)} variant="outline">
                                    Cancel
                                </Button>
                                <Button type="submit">Review & Submit</Button>
                            </div>
                        </form>
                    )}
                </DialogContent>
            </Dialog>
        </div>
    )
}


// export default function CreateCropStagesOfGrowth() {
//     const { crops, getAllCrops } = useCropContext()
//     const [cropStagesOfGrowth, setCropStagesOfGrowth] = useState<CropStagesOfGrowth>(initialCropStagesOfGrowth)
//     const [isDialogOpen, setIsDialogOpen] = useState(false)
//     const [isLoading, setIsLoading] = useState(false)
//     const [submitStatus, setSubmitStatus] = useState<"idle" | "success" | "error">("idle")
//     const [showSummary, setShowSummary] = useState(false)
//     const [searchTerm, setSearchTerm] = useState("")
//     const [selectedCrop, setSelectedCrop] = useState<Crop | null>(null)
//     const [isSearchOpen, setIsSearchOpen] = useState(false)
//
//     useEffect(() => {
//         getAllCrops()
//     }, [getAllCrops])
//
//     const filteredCrops = crops.filter((crop) => crop.name.toLowerCase().includes(searchTerm.toLowerCase()))
//
//     const handleCropSelect = (crop: Crop) => {
//         setSelectedCrop(crop)
//         setCropStagesOfGrowth((prev) => ({ ...prev, cropId: crop.id }))
//         setIsSearchOpen(false)
//     }
//
//     const handleStageChange = (index: number, field: keyof GrowthStage, value: any) => {
//         setCropStagesOfGrowth((prev) => ({
//             ...prev,
//             stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) => (i === index ? { ...stage, [field]: value } : stage)),
//         }))
//     }
//
//     const handlePeriodChange = (
//         index: number,
//         dateType: "stageStartDate" | "stageEndDate",
//         field: keyof Period,
//         value: any,
//     ) => {
//         setCropStagesOfGrowth((prev) => ({
//             ...prev,
//             stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) =>
//                 i === index
//                     ? {
//                         ...stage,
//                         [dateType]: { ...stage[dateType], [field]: field === "periodValue" ? Number(value) : value },
//                     }
//                     : stage,
//             ),
//         }))
//     }
//
//     const addStage = () => {
//         setCropStagesOfGrowth((prev) => ({
//             ...prev,
//             stagesOfGrowth: [
//                 ...prev.stagesOfGrowth,
//                 {
//                     stageStartDate: { periodUnit: PeriodUnit.DAYS, periodValue: 0 },
//                     stageEndDate: { periodUnit: PeriodUnit.DAYS, periodValue: 0 },
//                     stageOfGrowth: StagesOfGrowth.TRANSPLANTING,
//                     expanded: true,
//                 },
//             ],
//         }))
//     }
//
//     const removeStage = (index: number) => {
//         setCropStagesOfGrowth((prev) => ({
//             ...prev,
//             stagesOfGrowth: prev.stagesOfGrowth.filter((_, i) => i !== index),
//         }))
//     }
//
//     const toggleExpanded = (index: number) => {
//         setCropStagesOfGrowth((prev) => ({
//             ...prev,
//             stagesOfGrowth: prev.stagesOfGrowth.map((stage, i) =>
//                 i === index ? { ...stage, expanded: !stage.expanded } : stage,
//             ),
//         }))
//     }
//
//     const handleSubmit = async (e: React.FormEvent) => {
//         e.preventDefault()
//         setShowSummary(true)
//     }
//
//     const confirmSubmit = async () => {
//         setIsLoading(true)
//         setIsDialogOpen(false)
//         setShowSummary(false)
//
//         try {
//             // Simulate API call
//             await new Promise((resolve) => setTimeout(resolve, 2000))
//
//             // Here you would typically send the data to your API
//             // const response = await fetch('/api/crop-stages-of-growth', {
//             //   method: 'POST',
//             //   headers: { 'Content-Type': 'application/json' },
//             //   body: JSON.stringify(cropStagesOfGrowth)
//             // })
//             // if (!response.ok) throw new Error('Failed to submit crop stages of growth')
//
//             console.log("Submitting crop stages of growth:", cropStagesOfGrowth)
//             setSubmitStatus("success")
//         } catch (error) {
//             console.error("Error submitting crop stages of growth:", error)
//             setSubmitStatus("error")
//         } finally {
//             setIsLoading(false)
//         }
//     }
//
//     const resetForm = () => {
//         setSelectedCrop(null)
//         setCropStagesOfGrowth(initialCropStagesOfGrowth)
//         setSubmitStatus("idle")
//         setShowSummary(false)
//     }
//
//     const openDialog = () => setIsDialogOpen(true)
//
//     const renderSummary = () => (
//         <div className="space-y-6">
//             <h3 className="text-lg font-semibold">Summary</h3>
//             <div>
//                 <p className="font-medium">Crop: {selectedCrop?.name}</p>
//                 <p className="font-medium">Crop ID: {cropStagesOfGrowth.cropId}</p>
//             </div>
//             {cropStagesOfGrowth.stagesOfGrowth.map((stage, index) => (
//                 <Card key={index}>
//                     <CardHeader>
//                         <CardTitle>
//                             Stage {index + 1}: {stage.stageOfGrowth}
//                         </CardTitle>
//                     </CardHeader>
//                     <CardContent>
//                         <p>
//                             Start: {stage.stageStartDate.periodValue} {stage.stageStartDate.periodUnit}
//                         </p>
//                         <p>
//                             End: {stage.stageEndDate.periodValue} {stage.stageEndDate.periodUnit}
//                         </p>
//                     </CardContent>
//                 </Card>
//             ))}
//             <div className="flex justify-between">
//                 <Button onClick={() => setShowSummary(false)} variant="outline">
//                     Edit
//                 </Button>
//                 <Button onClick={confirmSubmit} disabled={isLoading}>
//                     {isLoading ? (
//                         <>
//                             <Loader2 className="mr-2 h-4 w-4 animate-spin" />
//                             Submitting...
//                         </>
//                     ) : (
//                         "Confirm & Submit"
//                     )}
//                 </Button>
//             </div>
//         </div>
//     )
//
//     let periodUnits = Object.keys(PeriodUnit);
//     let stagesOfGrowth = Object.keys(StagesOfGrowth);
//
//     return (
//         <div className="max-w-4xl mx-auto p-4">
//             <Card>
//                 <CardHeader>
//                     <CardTitle>Create Crop Stages of Growth</CardTitle>
//                     <CardDescription>Define the stages of growth for a crop</CardDescription>
//                 </CardHeader>
//                 <CardContent>
//                     {submitStatus === "idle" && (
//                         <div className="space-y-4">
//                             <div>
//                                 <Label htmlFor="cropSearch">Search Crop</Label>
//                                 <Popover open={isSearchOpen} onOpenChange={setIsSearchOpen}>
//                                     <PopoverTrigger asChild>
//                                         <Button
//                                             variant="outline"
//                                             role="combobox"
//                                             aria-expanded={isSearchOpen}
//                                             className="w-full justify-between"
//                                         >
//                                             {selectedCrop ? selectedCrop.name : "Select crop..."}
//                                             <Search className="ml-2 h-4 w-4 shrink-0 opacity-50" />
//                                         </Button>
//                                     </PopoverTrigger>
//                                     <PopoverContent className="w-[300px] p-0">
//                                         <Command>
//                                             <CommandInput placeholder="Search crops..." onValueChange={setSearchTerm} />
//                                             <CommandEmpty>No crop found.</CommandEmpty>
//                                             <CommandGroup>
//                                                 <CommandList>
//                                                     {filteredCrops.map((crop) => (
//                                                         <CommandItem key={crop.id} onSelect={() => handleCropSelect(crop)}>
//                                                             {crop.name}
//                                                         </CommandItem>
//                                                     ))}
//                                                 </CommandList>
//                                             </CommandGroup>
//                                         </Command>
//                                     </PopoverContent>
//                                 </Popover>
//                             </div>
//                             {selectedCrop && (
//                                 <Button onClick={openDialog} disabled={isLoading}>
//                                     Define Stages of Growth
//                                 </Button>
//                             )}
//                         </div>
//                     )}
//                     {submitStatus === "success" && (
//                         <Alert>
//                             <CheckCircle className="h-4 w-4" />
//                             <AlertTitle>Success</AlertTitle>
//                             <AlertDescription>Your crop stages of growth have been successfully submitted.</AlertDescription>
//                         </Alert>
//                     )}
//                     {submitStatus === "error" && (
//                         <Alert variant="destructive">
//                             <XCircle className="h-4 w-4" />
//                             <AlertTitle>Error</AlertTitle>
//                             <AlertDescription>
//                                 There was an error submitting your crop stages of growth. Please try again.
//                             </AlertDescription>
//                         </Alert>
//                     )}
//                 </CardContent>
//                 {submitStatus !== "idle" && (
//                     <CardFooter>
//                         <Button onClick={resetForm}>{submitStatus === "success" ? "Create Another" : "Try Again"}</Button>
//                     </CardFooter>
//                 )}
//             </Card>
//
//             <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
//                 <DialogContent className="max-w-4xl max-h-[80vh] overflow-y-auto">
//                     <DialogHeader>
//                         <DialogTitle>Create Crop Stages of Growth</DialogTitle>
//                         <DialogDescription>Define the stages of growth for {selectedCrop?.name}</DialogDescription>
//                     </DialogHeader>
//                     {showSummary ? (
//                         renderSummary()
//                     ) : (
//                         <form onSubmit={handleSubmit} className="space-y-6">
//                             {cropStagesOfGrowth.stagesOfGrowth.map((stage, index) => (
//                                 <Card key={index}>
//                                     <CardHeader>
//                                         <div className="flex justify-between items-center">
//                                             <CardTitle className="text-lg">Stage {index + 1}</CardTitle>
//                                             <Button variant="ghost" size="sm" onClick={() => toggleExpanded(index)}>
//                                                 {stage.expanded ? <Minimize2 className="h-4 w-4" /> : <Maximize2 className="h-4 w-4" />}
//                                             </Button>
//                                         </div>
//                                     </CardHeader>
//                                     <CardContent className="space-y-4">
//                                         {!stage.expanded && (
//                                             <div className="text-sm text-gray-500">
//                                                 {stage.stageOfGrowth} ({stage.stageStartDate.periodValue} {stage.stageStartDate.periodUnit} -{" "}
//                                                 {stage.stageEndDate.periodValue} {stage.stageEndDate.periodUnit})
//                                             </div>
//                                         )}
//                                         {stage.expanded && (
//                                             <>
//                                                 <div>
//                                                     <Label>Stage of Growth</Label>
//                                                     <Select
//                                                         value={stage.stageOfGrowth.toString()}
//                                                         onValueChange={(value) => handleStageChange(index, "stageOfGrowth", value)}
//                                                     >
//                                                         <SelectTrigger>
//                                                             <SelectValue placeholder="Select stage of growth" />
//                                                         </SelectTrigger>
//                                                         <SelectContent>
//                                                             <SelectItem value="GERMINATION">Germination</SelectItem>
//                                                             <SelectItem value="SEEDLING">Seedling</SelectItem>
//                                                             <SelectItem value="VEGETATIVE">Vegetative</SelectItem>
//                                                             <SelectItem value="BUDDING">Budding</SelectItem>
//                                                             <SelectItem value="FLOWERING">Flowering</SelectItem>
//                                                             <SelectItem value="RIPENING">Ripening</SelectItem>
//                                                             <SelectItem value="HARVESTING">Harvesting</SelectItem>
//                                                         </SelectContent>
//                                                     </Select>
//                                                 </div>
//                                                 <div className="grid grid-cols-2 gap-4">
//                                                     <div>
//                                                         <Label>Start Date</Label>
//                                                         <div className="flex space-x-2">
//                                                             <Input
//                                                                 type="number"
//                                                                 value={stage.stageStartDate.periodValue}
//                                                                 onChange={(e) =>
//                                                                     handlePeriodChange(index, "stageStartDate", "periodValue", e.target.value)
//                                                                 }
//                                                                 required
//                                                             />
//                                                             <Select
//                                                                 value={stage.stageStartDate.periodUnit.toString()}
//                                                                 onValueChange={(value) =>
//                                                                     handlePeriodChange(index, "stageStartDate", "periodUnit", value)
//                                                                 }
//                                                             >
//                                                                 <SelectTrigger>
//                                                                     <SelectValue placeholder="Unit" />
//                                                                 </SelectTrigger>
//                                                                 <SelectContent>
//                                                                     <SelectItem value="MINUTES">Minutes</SelectItem>
//                                                                     <SelectItem value="HOURS">Hours</SelectItem>
//                                                                     <SelectItem value="DAYS">Days</SelectItem>
//                                                                     <SelectItem value="WEEKS">Weeks</SelectItem>
//                                                                     <SelectItem value="MONTHS">Months</SelectItem>
//                                                                 </SelectContent>
//                                                             </Select>
//                                                         </div>
//                                                     </div>
//                                                     <div>
//                                                         <Label>End Date</Label>
//                                                         <div className="flex space-x-2">
//                                                             <Input
//                                                                 type="number"
//                                                                 value={stage.stageEndDate.periodValue}
//                                                                 onChange={(e) =>
//                                                                     handlePeriodChange(index, "stageEndDate", "periodValue", e.target.value)
//                                                                 }
//                                                                 required
//                                                             />
//                                                             <Select
//                                                                 value={stage.stageEndDate.periodUnit.toString()}
//                                                                 onValueChange={(value) =>
//                                                                     handlePeriodChange(index, "stageEndDate", "periodUnit", value)
//                                                                 }
//                                                             >
//                                                                 <SelectTrigger>
//                                                                     <SelectValue placeholder="Unit" />
//                                                                 </SelectTrigger>
//                                                                 <SelectContent>
//                                                                     <SelectItem value="MINUTES">Minutes</SelectItem>
//                                                                     <SelectItem value="HOURS">Hours</SelectItem>
//                                                                     <SelectItem value="DAYS">Days</SelectItem>
//                                                                     <SelectItem value="WEEKS">Weeks</SelectItem>
//                                                                     <SelectItem value="MONTHS">Months</SelectItem>
//                                                                 </SelectContent>
//                                                             </Select>
//                                                         </div>
//                                                     </div>
//                                                 </div>
//                                             </>
//                                         )}
//                                     </CardContent>
//                                     <CardFooter>
//                                         <Button
//                                             type="button"
//                                             variant="destructive"
//                                             onClick={() => removeStage(index)}
//                                             disabled={cropStagesOfGrowth.stagesOfGrowth.length === 1}
//                                         >
//                                             <Trash2 className="w-4 h-4 mr-2" />
//                                             Remove Stage
//                                         </Button>
//                                     </CardFooter>
//                                 </Card>
//                             ))}
//
//                             <Button type="button" onClick={addStage} className="w-full">
//                                 <Plus className="w-4 h-4 mr-2" />
//                                 Add Stage
//                             </Button>
//
//                             <div className="flex justify-between">
//                                 <Button type="button" onClick={() => setIsDialogOpen(false)} variant="outline">
//                                     Cancel
//                                 </Button>
//                                 <Button type="submit">Review & Submit</Button>
//                             </div>
//                         </form>
//                     )}
//                 </DialogContent>
//             </Dialog>
//         </div>
//     )
// }
