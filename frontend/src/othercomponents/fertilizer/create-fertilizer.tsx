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

export default function CreateFertilizer({isOpen, onClose}: FormProps) {

    const [fertilizers, setFertilizers] = useState<any[]>([])

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const formData = new FormData(event.currentTarget)
        const fertilizerData = Object.fromEntries(formData.entries());

        try {
            const response = await axios.post('http://localhost:8080/v1/api/fertilizer', fertilizerData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            setFertilizers([...fertilizers, response.data]);
        } catch (error) {
            console.error('Error creating fertilizer:', error);
        }
        const policyData = Object.fromEntries(formData.entries())
        setFertilizers([...fertilizers, policyData])
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
                        <DialogTitle>Add Fertilizer</DialogTitle>
                    </DialogHeader>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="name">Name</Label>
                                <Input id="name" name="name" placeholder="Enter Fertilizer Name"/>
                            </div>
                            <div className="space-y-2">
                                <Label htmlFor="alias">Alias</Label>
                                <Input id="alias" name="alias" placeholder="Enter Alternative Name"/>
                            </div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="composition">Composition</Label>
                                <Input id="composition" name="composition" placeholder="Enter Fertilizer Composition"/>
                            </div>
                            <div className="space-y-2">
                                <Label htmlFor="remarks">Remarks</Label>
                                <Input id="remarks" name="remarks" type="remarks"
                                       placeholder="Enter Remarks"/>
                            </div>
                            <div className="space-y-2">
                            </div>
                        </div>
                        <Button type="submit">Save Fertilizer</Button>
                    </form>
                </DialogContent>
            </Dialog>
            {fertilizers.length > 0 && (
                <div className="mt-4">
                    <h3 className="font-semibold mb-2">Added Fertilizers:</h3>
                    <ul className="list-disc pl-5">
                        {fertilizers.map((policy, index) => (
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

