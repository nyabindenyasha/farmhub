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
import {FormProps} from "@/lib/types";
import axios from "axios";

export default function CreatePesticide({isOpen, onClose}: FormProps) {

    const [pesticides, setPesticides] = useState<any[]>([])

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
                                <Label htmlFor="family">Family</Label>
                                <Input id="family" name="family" placeholder="Enter Pesticide Family"/>
                            </div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="genus">Genus</Label>
                                <Input id="genus" name="genus" placeholder="Enter Pesticide Genus"/>
                            </div>
                            <div className="space-y-2">
                                <Label htmlFor="species">Species</Label>
                                <Input id="species" name="species" type="species" placeholder="Enter Pesticide Species"/>
                            </div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="subSpecies">Sub Species</Label>
                                <Input id="subSpecies" name="subSpecies" placeholder="Enter Pesticide Sub Species"/>
                            </div>
                            <div className="space-y-2">
                            </div>
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

