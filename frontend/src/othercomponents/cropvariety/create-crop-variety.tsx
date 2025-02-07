import type React from "react"
import {useState} from "react"
import {useCropVarietyContext} from "@/context/CropVarietyContext"
import {Button} from "@/components/ui/button"
import {Input} from "@/components/ui/input"
import {Label} from "@/components/ui/label"
import {Textarea} from "@/components/ui/textarea"
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card"
import {Dialog, DialogContent, DialogHeader, DialogTitle} from "@/components/ui/dialog"
import {Alert, AlertDescription, AlertTitle} from "@/components/ui/alert"
import {CheckCircle, XCircle} from "lucide-react"
import {Crop} from "@/lib/types/crop";
import {CropSelector} from "@/othercomponents/shared/crop-selector";
import {FormProps} from "@/lib/types";
import {CropVarietySummary} from "@/othercomponents/cropvariety/crop-variety-summary";
import {DialogProps} from "@/lib/types/dialog-props";

export interface CropVarietyRequest {
    cropId: number
    variety: string
    maturityStartDay: number
    maturityEndDay: number
    harvestDuration: number
    remarks: string
}

export default function CreateCropVariety({isOpen, onClose}: FormProps) {

    const [step, setStep] = useState(1)
    const {createCropVariety} = useCropVarietyContext()
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
    const [isDialogOpen, setIsDialogOpen] = useState(false)

    const dialogProps: DialogProps = {
        width: 625,
        title: "Create Crop Batch"
    }

    const handleCropSelect = (crop: Crop) => {
        setSelectedCrop(crop)
        setCropVariety((prev) => ({...prev, cropId: crop.id}))
    }

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target
        setCropVariety((prev) => ({
            ...prev,
            [name]: name.includes("Day") || name === "harvestDuration" ? Number.parseInt(value) : value,
        }))
    }

    const handleSubmit = async (e: React.FormEvent) => {
        console.log("### GEDE")
        e.preventDefault()
        setIsLoading(true)
        try {
            await createCropVariety(cropVariety)
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
                    <div className="space-y-4">
                        <div>
                            <Label htmlFor="variety">Variety Name</Label>
                            <Input
                                id="variety"
                                name="variety"
                                value={cropVariety.variety}
                                onChange={handleInputChange}
                                required
                            />
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
                            <Textarea id="remarks" name="remarks" value={cropVariety.remarks}
                                      onChange={handleInputChange}/>
                        </div>
                    </div>
                )
            // case 3:
            //     return <CropVarietySummary crop={selectedCrop} cropVariety={cropVariety} onPrev={prevStep}
            //                                onSubmit={handleSubmit}/>
            default:
                return null
        }
    }

    return (step < 3) ? (
            <div className="max-w-4xl mx-auto p-4">
                <Card className="w-full max-w-2xl mx-auto">
                    <CardHeader>
                        <CardTitle>Create Crop Variety</CardTitle>
                        <CardDescription>Add a new variety for an existing crop</CardDescription>
                    </CardHeader>
                    <CardContent>
                        <div className="flex justify-between items-center">
                            <div>Step {step} of 3</div>
                            <Button
                                onClick={openDialog}>{step === 3 ? "Review" : step === 1 ? "Start" : "Continue"}</Button>
                        </div>
                        {submitStatus === "success" && (
                            <Alert className="mt-4">
                                <CheckCircle className="h-4 w-4"/>
                                <AlertTitle>Success</AlertTitle>
                                <AlertDescription>Crop variety has been successfully created.</AlertDescription>
                            </Alert>
                        )}
                        {submitStatus === "error" && (
                            <Alert variant="destructive" className="mt-4">
                                <XCircle className="h-4 w-4"/>
                                <AlertTitle>Error</AlertTitle>
                                <AlertDescription>There was an error creating the crop variety. Please try
                                    again.</AlertDescription>
                            </Alert>
                        )}
                    </CardContent>
                    <CardFooter>
                        {submitStatus !== "idle" && (
                            <Button onClick={resetForm} className="w-full">
                                {submitStatus === "success" ? "Create Another Variety" : "Try Again"}
                            </Button>
                        )}
                    </CardFooter>
                </Card>


                <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
                    <DialogContent className="sm:max-w-[425px]">
                        <DialogHeader>
                            <DialogTitle>Create Crop Variety</DialogTitle>
                        </DialogHeader>
                        <form>
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
                                    <Button type="submit">Submit</Button>
                                )}
                            </div>
                        </form>
                    </DialogContent>
                </Dialog>
            </div>
        )
        :
        <div className="max-w-4xl mx-auto p-4">
            <Card className="w-full max-w-2xl mx-auto">
                <CardHeader>
                    <CardTitle>Create Crop Variety</CardTitle>
                    <CardDescription>Add a new variety for an existing crop</CardDescription>
                </CardHeader>
                <CardContent>
                    <div className="flex justify-between items-center">
                        <div>Step {step} of 3</div>
                        <Button
                            onClick={openDialog}>{step === 3 ? "Review" : step === 1 ? "Start" : "Continue"}</Button>
                    </div>
                </CardContent>
            </Card>
            <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
                <DialogContent className="sm:max-w-[425px]">
                    <DialogHeader>
                        <DialogTitle>Create Crop Variety</DialogTitle>
                    </DialogHeader>
                    <CropVarietySummary crop={selectedCrop} cropVariety={cropVariety}/>
                    <div className="flex justify-between mt-6">
                        <Button type="button" onClick={prevStep} variant="outline">
                            Previous
                        </Button>
                        <Button type="submit" onClick={handleSubmit}>Submit</Button>
                    </div>
                </DialogContent>
            </Dialog>
        </div>
}

