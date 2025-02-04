import React, {useEffect, useState} from "react";
import {useRouter} from "next/router";
import {patients} from "@/lib/data";
import DashboardLayout from "@/layouts/DashboardLayout";
import {Button} from "@/components/ui/button";
import PrimaryButton from "@/components/buttons/customButton";
import {MoreHorizontal, Plus, Search} from "lucide-react";
import {Tabs, TabsList, TabsTrigger} from "@/components/ui/tabs";
import {Badge} from "@/components/ui/badge";
import {Input} from "@/components/ui/input";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Checkbox} from "@/components/ui/checkbox";
import {DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger} from "@/components/ui/dropdown-menu";
import {useCropContext} from "@/context/CropContext";
import CreateCropStagesOfGrowth from "@/othercomponents/cropstages/create-crop-stages-of-growth";

export default function CropStagesOfGrowthComponent() {

    const {crops, getAllCrops} = useCropContext();

    useEffect(() => {
        getAllCrops();
    }, [getAllCrops]);

    const [isPolicyFormOpen, setIsPolicyFormOpen] = useState(false)
    const openPolicyForm = () => setIsPolicyFormOpen(true)
    const closePolicyForm = () => setIsPolicyFormOpen(false)

    const [selectedClients, setSelectedClients] = useState<string[]>([])
    const [searchTerm, setSearchTerm] = useState("")
    const router = useRouter()

    const filteredClients = patients.filter((patient) =>
        patient.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        patient.caseRef.toLowerCase().includes(searchTerm.toLowerCase()) ||
        patient.source.toLowerCase().includes(searchTerm.toLowerCase())
    )

    const handleSelectAll = () => {
        if (selectedClients.length === filteredClients.length) {
            setSelectedClients([])
        } else {
            setSelectedClients(filteredClients.map((client) => client.id))
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
                    <h2 className="text-3xl font-bold tracking-tight">Crops</h2>
                    <div className="flex items-center space-x-2">
                        <Button variant="outline">Export</Button>
                        <PrimaryButton secondary={true} text={"Add Crop"} onClick={openPolicyForm}
                                       icon={<Plus className="h-4 w-4"/>}/>
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
                                <TableHead>Name</TableHead>
                                <TableHead>Family</TableHead>
                                <TableHead>Genus</TableHead>
                                <TableHead>Species</TableHead>
                                <TableHead>Sub Species</TableHead>

                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {crops.slice(0, 10).map((crop) => (
                                <TableRow key={crop.id}>
                                    <TableCell>
                                        <Checkbox
                                            checked={selectedClients.includes(String(crop.id))}
                                            onCheckedChange={() => handleSelectClient(String(crop.id))}
                                        />
                                    </TableCell>
                                    <TableCell>{crop.name}</TableCell>
                                    <TableCell>{crop.family}</TableCell>
                                    <TableCell>{crop.genus}</TableCell>
                                    <TableCell>{crop.species}</TableCell>
                                    <TableCell>{crop.subSpecies}</TableCell>
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
                                                        onClick={() => router.push(`/dashboard/patient/${'adsfljkl'}`)}> View Details</span>
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