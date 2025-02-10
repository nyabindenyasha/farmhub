import type React from "react"
import {useEffect, useState} from "react"
import {Button} from "@/components/ui/button"
import {Input} from "@/components/ui/input"
import {Label} from "@/components/ui/label"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select"
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card"
import {Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle} from "@/components/ui/dialog"
import {Alert, AlertDescription, AlertTitle} from "@/components/ui/alert"
import {CheckCircle, Loader2, Maximize2, Minimize2, Plus, PlusCircle, Search, Trash2, X, XCircle} from "lucide-react"
import {Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList} from "@/components/ui/command"
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover"
import {Period} from "@/lib/types/period";
import {StagesOfGrowth} from "@/lib/enums/stages-of-growth";
import {PeriodUnit} from "@/lib/enums/period-unit";
import {useCropContext} from "@/context/CropContext";
import {Crop} from "@/lib/types/crop";
import {FormProps} from "@/lib/types";
import {useCropStagesOfGrowthContext} from "@/context/CropStagesOfGrowthContext";
import {DialogProps} from "@/lib/types/dialog-props";
import {CropSelector} from "@/othercomponents/shared/crop-selector";
import {Textarea} from "@/components/ui/textarea";

interface GrowthStage {
    stageStartDate: Period
    stageEndDate: Period
    stageOfGrowth: StagesOfGrowth
    expanded: boolean
}

export interface CropStagesOfGrowthRequest {
    cropId: number
    cropStages: GrowthStage[]
}

const initialCropStagesOfGrowth: CropStagesOfGrowthRequest = {
    cropId: 0,
    cropStages: [
        {
            stageStartDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
            stageEndDate: {periodUnit: PeriodUnit.DAYS, periodValue: 1},
            stageOfGrowth: StagesOfGrowth.TRANSPLANTING,
            expanded: true,
        },
    ],
}

export default function CreateCropStagesOfGrowth({isOpen, onClose}: FormProps) {
    const [step, setStep] = useState(1)
    const {createCropStagesOfGrowth} = useCropStagesOfGrowthContext();
    const [selectedCrop, setSelectedCrop] = useState<Crop | null>(null)
    const [cropStagesOfGrowth, setCropStagesOfGrowth] = useState<CropStagesOfGrowthRequest>(initialCropStagesOfGrowth)
    const [isDialogOpen, setIsDialogOpen] = useState(false)
    const [isLoading, setIsLoading] = useState(false)
    const [submitStatus, setSubmitStatus] = useState<"idle" | "success" | "error">("idle")

    const dialogProps: DialogProps = {
        width: 625,
        title: "Create Crop Batch"
    }

    const handleCropSelect = (crop: Crop) => {
        setSelectedCrop(crop)
        setCropStagesOfGrowth((prev) => ({...prev, cropId: crop.id}))
    }

    const handleStageChange = (index: number, field: keyof GrowthStage, value: any) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            cropStages: prev.cropStages.map((stage, i) => (i === index ? {...stage, [field]: value} : stage)),
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
            cropStages: prev.cropStages.map((stage, i) =>
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
            cropStages: [
                ...prev.cropStages,
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
            cropStages: prev.cropStages.filter((_, i) => i !== index),
        }))
    }

    const toggleExpanded = (index: number) => {
        setCropStagesOfGrowth((prev) => ({
            ...prev,
            cropStages: prev.cropStages.map((stage, i) =>
                i === index ? {...stage, expanded: !stage.expanded} : stage,
            ),
        }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setIsLoading(true)
        setIsDialogOpen(false)

        setIsLoading(true)
        try {
            await createCropStagesOfGrowth(cropStagesOfGrowth)
            setSubmitStatus("success")
            setIsDialogOpen(false)
            setStep(1)
        } catch (error) {
            console.error("Error creating crop variety:", error)
            setSubmitStatus("error")
        } finally {
            setIsLoading(false)
            setStep(1)
        }
    }

    const resetForm = () => {
        setCropStagesOfGrowth(initialCropStagesOfGrowth)
        setSubmitStatus("idle")
    }

    const nextStep = () => {
        if (step < 4) {
            setStep((prev) => prev + 1)
        } else {
            setStep(1)
            setIsDialogOpen(false)
        }
    }

    const prevStep = () => setStep((prev) => Math.max(prev - 1, 1))

    const openDialog = () => setIsDialogOpen(true)

    const getDialogContent = () => {
        switch (step) {
            case 1:
                return (
                    <CropSelector dialogProps={dialogProps} onCropSelect={handleCropSelect}/>
                )
            case 2:
                return (
                    <form onSubmit={handleSubmit} className="space-y-6">

                        {cropStagesOfGrowth.cropStages.map((stage, index) => (
                            <div key={index} className="flex items-center mt-2">
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

                                    {/*<CardFooter>*/}
                                    {/*    <Button*/}
                                    {/*        type="button"*/}
                                    {/*        variant="destructive"*/}
                                    {/*        onClick={() => removeStage(index)}*/}
                                    {/*        disabled={cropStagesOfGrowth.cropStages.length === 1}*/}
                                    {/*    >*/}
                                    {/*        <Trash2 className="w-4 h-4 mr-2"/>*/}
                                    {/*        Remove Stage*/}
                                    {/*    </Button>*/}
                                    {/*</CardFooter>*/}
                                </Card>
                                <Button
                                    type="button"
                                    variant="ghost"
                                    size="icon"
                                    onClick={() => removeStage(index)}
                                    disabled={cropStagesOfGrowth.cropStages.length === 1}
                                    className="ml-2"
                                >
                                    <X className="h-4 w-4"/>
                                </Button>
                            </div>
                        ))}

                        {/*<Button type="button" onClick={addStage} className="w-full">*/}
                        {/*    <Plus className="w-4 h-4 mr-2"/>*/}
                        {/*    Add Stage*/}
                        {/*</Button>*/}

                        <Button
                            type="button"
                            variant="outline"
                            size="sm"
                            onClick={addStage}
                            className="mt-2"
                        >
                            <PlusCircle className="h-4 w-4 mr-2"/>
                            Add Stage
                        </Button>

                        {/*<div className="flex justify-between">*/}
                        {/*    <Button type="button" onClick={() => setIsDialogOpen(false)} variant="outline">*/}
                        {/*        Cancel*/}
                        {/*    </Button>*/}
                        {/*    <Button type="submit">Review & Submit</Button>*/}
                        {/*</div>*/}
                    </form>
                )
            case 3:
                return renderSummary()
            default:
                return null
        }
    }

    const renderSummary = () => (
        <div className="space-y-6">
            <h3 className="text-lg font-semibold">Summary</h3>
            <div>
                <p className="font-medium">Crop: {selectedCrop?.name}</p>
            </div>
            {cropStagesOfGrowth.cropStages.map((stage, index) => (
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
                            <div>Step {step} of 3</div>
                            <Button
                                onClick={openDialog}>{step === 3 ? "Review" : step === 1 ? "Start" : "Continue"}</Button>
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
                <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
                    <DialogHeader>
                        <DialogTitle>Create Crop Stages of Growth</DialogTitle>
                        <DialogDescription>Define the stages of growth for your crop</DialogDescription>
                    </DialogHeader>
                    {/*{showSummary ? (*/}
                    {/*    renderSummary()*/}
                    {/*) : (*/}
                    {/*    getDialogContent()*/}
                    {/*)}*/}

                    {getDialogContent()}

                    <div className="flex justify-between mt-6">
                        {step > 1 && (
                            <Button type="button" onClick={prevStep} variant="outline">
                                Previous
                            </Button>
                        )}
                        {step < 3 ? (
                            <Button type="button" onClick={nextStep}>
                                Next
                            </Button>
                        ) : (
                            <Button type="submit" onClick={handleSubmit}>Submit</Button>
                        )}
                    </div>
                </DialogContent>
            </Dialog>
        </div>
    )
}