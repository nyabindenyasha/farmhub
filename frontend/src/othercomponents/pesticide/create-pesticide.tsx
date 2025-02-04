'use client'

import {useState} from 'react'
import {Button} from '@/components/ui/button'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,

} from '@/components/ui/dialog'
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from '@/components/ui/select'
import {FormProps} from "@/lib/types";
import axios from "axios";
import {useForm} from "react-hook-form";
import {Pesticide} from "@/lib/types/pesticide";
import {PlusCircle, X} from "lucide-react";
import {PesticideType} from "@/lib/enums/pesticide-type";
import {PesticideModeOfAction} from "@/lib/enums/pesticide-mode-of-action";

export default function CreatePesticide({isOpen, onClose}: FormProps) {

    const [pesticides, setPesticides] = useState<any[]>([]);

    const {
        formState: { errors },
    } = useForm<Pesticide>()
    const [activeIngredients, setActiveIngredients] = useState<string[]>([""])
    const [targetPests, setTargetPests] = useState<string[]>([""])
    const [targetDiseases, setTargetDiseases] = useState<string[]>([""])

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const formData = new FormData(event.currentTarget)
        const pesticideData = Object.fromEntries(formData.entries());

        try {
            const response = await axios.post('http://localhost:8080/v1/api/pesticide', pesticideData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setPesticides([...pesticides, response.data]);
        } catch (error) {
            console.error('Error creating pesticide:', error);
        }
        const policyData = Object.fromEntries(formData.entries())
        setPesticides([...pesticides, policyData])
    }

    const handleArrayInput = (index: number, value: string, setter: React.Dispatch<React.SetStateAction<string[]>>) => {
        setter((prev) => {
            const newArray = [...prev]
            newArray[index] = value
            return newArray
        })
    }

    const addArrayItem = (setter: React.Dispatch<React.SetStateAction<string[]>>) => {
        setter((prev) => [...prev, ""])
    }

    const removeArrayItem = (index: number, setter: React.Dispatch<React.SetStateAction<string[]>>) => {
        setter((prev) => prev.filter((_, i) => i !== index))
    }

    let appointmentTypes = Object.keys(PesticideType);

    let modeOfAction = Object.keys(PesticideModeOfAction);

    console.log("#### appointmentTypes: ", appointmentTypes);

    return (
        // <Card>
        //     <CardHeader>
        //         <CardTitle>Policy Information</CardTitle>
        //     </CardHeader>
        //     <CardContent>
        <div>
            <Dialog open={isOpen} onOpenChange={onClose}>
                <DialogContent className="sm:max-w-[600px]">
                    <DialogHeader>
                        <DialogTitle>Add Pesticide</DialogTitle>
                    </DialogHeader>
                    <form onSubmit={handleSubmit} className="space-y-4">

                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="name">Name</Label>
                                <Input id="name" name="name" placeholder="Enter Pesticide Name"/>
                            </div>
                            <div className="space-y-2">
                                <Label htmlFor="alias">Alias</Label>
                                <Input id="alias" name="alias" placeholder="Enter Alternative Name"/>
                            </div>
                        </div>

                        {/*active ingredients*/}

                        <div>
                            <Label>Active Ingredients</Label>
                            {activeIngredients.map((ingredient, index) => (
                                <div key={index} className="flex items-center mt-2">
                                    <Input
                                        value={ingredient}
                                        onChange={(e) => handleArrayInput(index, e.target.value, setActiveIngredients)}
                                        className="flex-grow"
                                    />
                                    <Button
                                        type="button"
                                        variant="ghost"
                                        size="icon"
                                        onClick={() => removeArrayItem(index, setActiveIngredients)}
                                        className="ml-2"
                                    >
                                        <X className="h-4 w-4" />
                                    </Button>
                                </div>
                            ))}
                            <Button
                                type="button"
                                variant="outline"
                                size="sm"
                                onClick={() => addArrayItem(setActiveIngredients)}
                                className="mt-2"
                            >
                                <PlusCircle className="h-4 w-4 mr-2" />
                                Add Ingredient
                            </Button>
                        </div>


                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="applicationRate">Application Rate</Label>
                                <Input id="applicationRate" name="applicationRate" placeholder="Enter Application Rate"/>
                            </div>
                            <div className="space-y-2">
                                <Label htmlFor="safetyInterval">Safety Interval (Days)</Label>
                                <Input id="safetyInterval" name="safetyInterval" type="number" placeholder="7"/>
                            </div>
                        </div>

                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="pesticideType">Pesticide Type</Label>
                                <Select name="pesticideType">
                                    <SelectTrigger id="pesticideType">
                                        <SelectValue placeholder="Select Pesticide Type" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {appointmentTypes.map((pesticideType) => (
                                            <SelectItem value={pesticideType}>
                                                {pesticideType}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>


                            <div className="space-y-2">
                                <Label htmlFor="modeOfAction">Mode Of Action</Label>
                                <Select name="modeOfAction">
                                    <SelectTrigger id="modeOfAction">
                                        <SelectValue placeholder="Select Mode Of Action" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {modeOfAction.map((modeOfAction) => (
                                            <SelectItem value={modeOfAction}>
                                                {modeOfAction}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>
                        </div>

                        <div>
                            <Label>Target Pests</Label>
                            {targetPests.map((pest, index) => (
                                <div key={index} className="flex items-center mt-2">
                                    <Input
                                        value={pest}
                                        onChange={(e) => handleArrayInput(index, e.target.value, setTargetPests)}
                                        className="flex-grow"
                                    />
                                    <Button
                                        type="button"
                                        variant="ghost"
                                        size="icon"
                                        onClick={() => removeArrayItem(index, setTargetPests)}
                                        className="ml-2"
                                    >
                                        <X className="h-4 w-4" />
                                    </Button>
                                </div>
                            ))}
                            <Button type="button" variant="outline" size="sm" onClick={() => addArrayItem(setTargetPests)} className="mt-2">
                                <PlusCircle className="h-4 w-4 mr-2" />
                                Add Target Pest
                            </Button>
                        </div>

                        <div>
                            <Label>Target Diseases</Label>
                            {targetDiseases.map((disease, index) => (
                                <div key={index} className="flex items-center mt-2">
                                    <Input
                                        value={disease}
                                        onChange={(e) => handleArrayInput(index, e.target.value, setTargetDiseases)}
                                        className="flex-grow"
                                    />
                                    <Button
                                        type="button"
                                        variant="ghost"
                                        size="icon"
                                        onClick={() => removeArrayItem(index, setTargetDiseases)}
                                        className="ml-2"
                                    >
                                        <X className="h-4 w-4" />
                                    </Button>
                                </div>
                            ))}
                            <Button
                                type="button"
                                variant="outline"
                                size="sm"
                                onClick={() => addArrayItem(setTargetDiseases)}
                                className="mt-2"
                            >
                                <PlusCircle className="h-4 w-4 mr-2" />
                                Add Target Disease
                            </Button>
                        </div>


                        <Button type="submit">Save Pesticide</Button>
                    </form>
                </DialogContent>
            </Dialog>
            {pesticides.length > 0 && (
                <div className="mt-4">
                    <h3 className="font-semibold mb-2">Added Pesticides:</h3>
                    <ul className="list-disc pl-5">
                        {pesticides.map((policy, index) => (
                            <li key={index}>{policy.policyNumber} - {policy.firstName} {policy.lastName}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
        // {/*    </CardContent>*/}
        // {/*</Card>*/}
    )
}

