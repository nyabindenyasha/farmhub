'use client'

import {useEffect, useState} from 'react'
import {Button} from '@/components/ui/button'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle,} from '@/components/ui/dialog'
import {Textarea} from "@/components/ui/textarea";
import {CropScheduleType} from "@/lib/enums/crop-schedule-type";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import FertilizerScheduleForm, {FertilizerSchedule} from "@/othercomponents/cropprogram/fertilizer-schedule-form";
import PesticideScheduleForm, {PesticideSchedule} from "@/othercomponents/cropprogram/pesticide-schedule-form";
import {CropProgramSummary} from "@/othercomponents/cropprogram/crop-program-summary";
import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import {FormProps} from "@/lib/types";
import {useCropContext} from "@/context/CropContext";
import {Crop} from "@/lib/types/crop";

interface CropProgram {
    cropId: number
    name: string
    description: string
    source: string
    remarks: string
    cropScheduleType: CropScheduleType
    fertilizerScheduleRequests: FertilizerSchedule[]
    pesticideScheduleRequests: PesticideSchedule[]
}

const initialCropProgram: CropProgram = {
    cropId: 0,
    name: "",
    description: "",
    source: "",
    remarks: "",
    cropScheduleType: CropScheduleType.SECONDARY,
    fertilizerScheduleRequests: [],
    pesticideScheduleRequests: [],
}

export default function CreateCropProgram({isOpen, onClose}: FormProps) {

    const {crops, getAllCrops} = useCropContext()
    const [selectedCrop, setSelectedCrop] = useState<Crop | undefined>(undefined);
    const [search, setSearch] = useState("");
    const [step, setStep] = useState(1)
    const [cropProgram, setCropProgram] = useState<CropProgram>(initialCropProgram)
    const [isDialogOpen, setIsDialogOpen] = useState(false)

    useEffect(() => {
        getAllCrops()
    }, [getAllCrops])

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target
        setCropProgram((prev) => ({...prev, [name]: value}))
    }

    const handleSelectChange = (name: string, value: string) => {
        setCropProgram((prev) => ({...prev, [name]: value}))
    }

    const handleFertilizerScheduleChange = (schedules: FertilizerSchedule[]) => {
        setCropProgram((prev) => ({...prev, fertilizerScheduleRequests: schedules}))
    }

    const handlePesticideScheduleChange = (schedules: PesticideSchedule[]) => {
        setCropProgram((prev) => ({...prev, pesticideScheduleRequests: schedules}))
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        // Here you would typically send the data to your API
        console.log("Submitting crop program:", cropProgram)
        setIsDialogOpen(false)
    }

    const nextStep = () => {
        if (step < 5) {
            setStep((prev) => prev + 1)
        } else {
            setIsDialogOpen(false)
        }
    }

    const prevStep = () => setStep((prev) => Math.max(prev - 1, 1))

    const openDialog = () => setIsDialogOpen(true);

    const filteredItems = crops.filter(crop =>
        crop.name.toLowerCase().includes(search.toLowerCase())
    );

    const getDialogContent = () => {
        switch (step) {
            case 1:
                return (
                    <div className="space-y-4">
                        <form onSubmit={handleSubmit}>
                            <div>
                                <Label htmlFor="name">Crop</Label>
                                {/*<Select onValueChange={setSelectedCrop}>*/}
                                <Select
                                    onValueChange={(value) => setSelectedCrop(crops.find(item => item.name === value))}>
                                    <SelectTrigger className="w-full">
                                        <SelectValue placeholder="Select Crop"/>
                                    </SelectTrigger>
                                    <SelectContent className="max-h-40 overflow-y-auto">
                                        <div className="p-2">
                                            <Input
                                                placeholder="Search..."
                                                value={search}
                                                onChange={(e) => setSearch(e.target.value)}
                                                className="mb-2"
                                            />
                                        </div>
                                        {filteredItems.map(item => (
                                            <SelectItem key={item.name}
                                                        value={item.name}>{item.name.charAt(0).toUpperCase() + item.name.slice(1)}</SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                                {selectedCrop && (
                                    <p className="mt-2 text-sm text-gray-500">Selected: {selectedCrop.name}</p>
                                )}
                            </div>


                            <div>
                                <Label htmlFor="name">Name</Label>
                                <Input id="name" name="name" value={cropProgram.name} onChange={handleInputChange}
                                       required/>
                            </div>

                            <div>
                                <Label htmlFor="description">Description</Label>
                                <Textarea
                                    id="description"
                                    name="description"
                                    value={cropProgram.description}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>


                            <div>
                                <Label htmlFor="source">Source</Label>
                                <Input id="source" name="source" value={cropProgram.source} onChange={handleInputChange}
                                       required/>
                            </div>

                            <div>
                                <Label htmlFor="remarks">Remarks</Label>
                                <Textarea id="remarks" name="remarks" value={cropProgram.remarks}
                                          onChange={handleInputChange}/>
                            </div>


                            <div>
                                <Label htmlFor="cropScheduleType">Crop Schedule Type</Label>
                                <Select
                                    name="cropScheduleType"
                                    value={cropProgram.cropScheduleType.toString()}
                                    onValueChange={(value) => handleSelectChange("cropScheduleType", value)}
                                >
                                    <SelectTrigger>
                                        <SelectValue placeholder="Select crop schedule type"/>
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem
                                            value={CropScheduleType.SECONDARY.toString()}>{CropScheduleType.SECONDARY}</SelectItem>
                                        <SelectItem
                                            value={CropScheduleType.TERTIARY.toString()}>{CropScheduleType.TERTIARY}</SelectItem>
                                    </SelectContent>
                                </Select>
                            </div>
                        </form>
                    </div>
                )
            case 2:
                return (
                    <FertilizerScheduleForm
                        schedules={cropProgram.fertilizerScheduleRequests}
                        onChange={handleFertilizerScheduleChange}
                    />
                )
            case 3:
                return (
                    <PesticideScheduleForm
                        schedules={cropProgram.pesticideScheduleRequests}
                        onChange={handlePesticideScheduleChange}
                    />
                )
            case 4:
                return <CropProgramSummary crop={selectedCrop} cropProgram={cropProgram}/>
            default:
                return null
        }
    }

    return (
        <div className="max-w-4xl mx-auto p-4">
            <Card>
                <CardHeader>
                    <CardTitle>Create Crop Program</CardTitle>
                    <CardDescription>Follow the steps to create your crop program</CardDescription>
                </CardHeader>
                <CardContent>
                    <div className="flex justify-between items-center">
                        <div>Step {step} of 4</div>
                        <Button
                            onClick={openDialog}>{step === 4 ? "Review" : step === 1 ? "Start" : "Continue"}</Button>
                    </div>
                </CardContent>
            </Card>

            <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
                <DialogContent className={"max-w-4xl max-h-[80vh] overflow-y-auto"}>
                    <DialogHeader>
                        <DialogTitle>
                            Step {step}:{" "}
                            {step === 1
                                ? "Basic Information"
                                : step === 2
                                    ? "Fertilizer Schedules"
                                    : step === 3
                                        ? "Pesticide Schedules"
                                        : "Review"}
                        </DialogTitle>
                        <DialogDescription>
                            {step === 4
                                ? "Review your crop program details before submitting"
                                : "Please fill in the required information"}
                        </DialogDescription>
                    </DialogHeader>
                    {getDialogContent()}
                    <div className="flex justify-between mt-6">
                        {step > 1 && (
                            <Button type="button" onClick={prevStep} variant="outline">
                                Previous
                            </Button>
                        )}
                        {step < 4 ? (
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

