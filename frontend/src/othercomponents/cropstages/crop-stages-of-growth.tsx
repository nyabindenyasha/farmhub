import React, {useEffect, useState} from "react";
import {useRouter} from "next/router";
import DashboardLayout from "@/layouts/DashboardLayout";
import {Button} from "@/components/ui/button";
import {MoreHorizontal, Plus, Search} from "lucide-react";
import {Tabs, TabsList, TabsTrigger} from "@/components/ui/tabs";
import {Badge} from "@/components/ui/badge";
import {Input} from "@/components/ui/input";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Checkbox} from "@/components/ui/checkbox";
import {DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger} from "@/components/ui/dropdown-menu";
import CreateCropStagesOfGrowth from "@/othercomponents/cropstages/create-crop-stages-of-growth";
import {useCropStagesOfGrowthContext} from "@/context/CropStagesOfGrowthContext";

export default function CropStagesOfGrowthComponent() {

    const {cropStagesOfGrowths, getAllCropStagesOfGrowths} = useCropStagesOfGrowthContext();


    const [isPolicyFormOpen, setIsPolicyFormOpen] = useState(false)
    const openPolicyForm = () => setIsPolicyFormOpen(true)
    const closePolicyForm = () => setIsPolicyFormOpen(false)

    useEffect(() => {
        getAllCropStagesOfGrowths();
    }, [getAllCropStagesOfGrowths]);

    const [selectedClients, setSelectedClients] = useState<string[]>([])
    const [searchTerm, setSearchTerm] = useState("")
    const router = useRouter()

    const filteredClients = cropStagesOfGrowths.filter((cropStageOfGrowth) =>
        cropStageOfGrowth.crop.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        cropStageOfGrowth.stageOfGrowth.toLowerCase().includes(searchTerm.toLowerCase()) ||
        cropStageOfGrowth.stageStartDate.periodUnit.toLowerCase().includes(searchTerm.toLowerCase()) ||
        cropStageOfGrowth.stageEndDate.periodUnit.toLowerCase().includes(searchTerm.toLowerCase())
    )

    const handleSelectAll = () => {
        if (selectedClients.length === filteredClients.length) {
            setSelectedClients([])
        } else {
            setSelectedClients(filteredClients.map((client) => client.crop.name))
        }
    }

    const handleSelectClient = (clientId: string) => {
        if (selectedClients.includes(clientId)) {
            setSelectedClients(selectedClients.filter((id) => id !== clientId))
        } else {
            setSelectedClients([...selectedClients, clientId])
        }
    }

    const handleDelete = (clientId: string) => {
        // Handle delete logic here
        console.log(`Deleting client ${clientId}`)
    }

    return (
        <DashboardLayout>
            <div className="flex w-screen p-5 space-y-6 min-h-screen flex-col">
                <div className="flex items-center justify-between">
                    <h2 className="text-3xl font-bold tracking-tight">Crops stages</h2>
                    <div className="flex items-center space-x-2">
                        <Button variant="outline">Export</Button>
                        <CreateCropStagesOfGrowth isOpen={isPolicyFormOpen} onClose={closePolicyForm}/>
                    </div>
                </div>
                <div className="flex items-center justify-between">
                    <div className="flex items-center gap-4">
                        <Tabs defaultValue="upcoming">
                            <TabsList>
                                <TabsTrigger value="upcoming">
                                    Upcoming
                                    <Badge variant="secondary" className="ml-2">6</Badge>
                                </TabsTrigger>
                                <TabsTrigger value="pending">
                                    Pending
                                    <Badge variant="secondary" className="ml-2">3</Badge>
                                </TabsTrigger>
                                <TabsTrigger value="past">
                                    Past
                                    <Badge variant="secondary" className="ml-2">9</Badge>
                                </TabsTrigger>
                            </TabsList>
                        </Tabs>
                    </div>
                </div>
                <div className="flex items-center justify-between">
                    <div className="flex items-center space-x-2">
                        <Search className="w-4 h-4 text-muted-foreground"/>
                        <Input
                            placeholder="Search for clients..."
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            className="w-[300px]"
                        />
                    </div>
                    <Button variant="outline">
                        Filters
                    </Button>
                </div>
                <div className="rounded-md border">
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHead className="w-[50px]">
                                    <Checkbox
                                        checked={selectedClients.length === filteredClients.length}
                                        onCheckedChange={handleSelectAll}
                                    />
                                </TableHead>
                                <TableHead>Crop Name</TableHead>
                                <TableHead>Stage</TableHead>
                                <TableHead>Stage Start Date</TableHead>
                                <TableHead>Stage End Date</TableHead>
                                <TableHead>Actions</TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {cropStagesOfGrowths.slice(0, 10).map((cropStageOfGrowth) => (
                                <TableRow key={cropStageOfGrowth.id}>
                                    <TableCell>
                                        <Checkbox
                                            checked={selectedClients.includes(String(cropStageOfGrowth.id))}
                                            onCheckedChange={() => handleSelectClient(String(cropStageOfGrowth.id))}
                                        />
                                    </TableCell>
                                    <TableCell>{cropStageOfGrowth.crop.name}</TableCell>
                                    <TableCell>{cropStageOfGrowth.stageOfGrowth}</TableCell>
                                    <TableCell>{cropStageOfGrowth.stageStartDate.periodValue} {cropStageOfGrowth.stageStartDate.periodUnit}</TableCell>
                                    <TableCell>{cropStageOfGrowth.stageEndDate.periodValue} {cropStageOfGrowth.stageEndDate.periodUnit}</TableCell>
                                    <TableCell>
                                        <DropdownMenu>
                                            <DropdownMenuTrigger asChild>
                                                <Button variant="ghost" size="icon">
                                                    <MoreHorizontal className="h-4 w-4"/>
                                                </Button>
                                            </DropdownMenuTrigger>
                                            <DropdownMenuContent align="end">
                                                <DropdownMenuItem>
                                                    <span
                                                        onClick={() => {
                                                        }}> View Details</span>
                                                </DropdownMenuItem>
                                                <DropdownMenuItem>Edit Record</DropdownMenuItem>
                                            </DropdownMenuContent>
                                        </DropdownMenu>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </div>
                <div className="flex items-center justify-between">
                    <Button variant="outline" size="sm">
                        Previous
                    </Button>
                    <div className="flex items-center space-x-2">
                        <Button variant="outline" size="sm" className="h-8 w-8 p-0">
                            1
                        </Button>
                        <Button variant="outline" size="sm" className="h-8 w-8 p-0">
                            2
                        </Button>
                        <span>...</span>
                        <Button variant="outline" size="sm" className="h-8 w-8 p-0">
                            9
                        </Button>
                        <Button variant="outline" size="sm" className="h-8 w-8 p-0">
                            10
                        </Button>
                    </div>
                    <Button variant="outline" size="sm">
                        Next
                    </Button>
                </div>

            </div>
        </DashboardLayout>
    )
}