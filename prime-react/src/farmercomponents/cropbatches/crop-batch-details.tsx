"use client"

import {useEffect, useState} from "react"
import {Dialog, DialogContent, DialogHeader, DialogTitle} from "@/components/ui/dialog"
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table"
import {Checkbox} from "@/components/ui/checkbox"
import {Button} from "@/components/ui/button"
import {Input} from "@/components/ui/input"
import {useCropBatchContext} from "@/context/CropBatchContext";
import {CropBatch} from "@/lib/types/crop-batch";
import {ScheduleTask} from "@/lib/types/schedule-task";
import {TaskStatus} from "@/lib/enums/task-status";

interface CropBatchDetailsProps {
    batchId: number
    onClose: () => void
}

export default function CropBatchDetails({batchId, onClose}: CropBatchDetailsProps) {

    const {getCropBatchById} = useCropBatchContext();

    const [batch, setBatch] = useState<CropBatch | null>(null);

    // Fetch batch data when component mounts or batchId changes
    // Fetch batch data only when batchId changes
    useEffect(() => {
        let isMounted = true;
        console.log("CropBatchDetails");

        const fetchBatch = async () => {
            try {
                // setLoading(true);
                const batchData = await getCropBatchById(batchId);
                if (batchData && isMounted) {
                    setBatch(batchData);
                    console.log("Batch data received:", batchData);
                } else if (isMounted) {
                    // setError("Batch not found");
                    console.log("Batch not found")
                }
            } catch (err) {
                if (isMounted) {
                    // setError("Error fetching cropbatch details");
                    console.log("Error fetching cropbatch details:", err)
                }
            } finally {
                if (isMounted) {
                    // setLoading(false);
                    console.log("Finally")
                }
            }
        };

        fetchBatch();

        // Cleanup function to prevent setting state on unmounted component
        return () => {
            isMounted = false;
        };
    }, [batchId]); // Remove getCropBatchById from dependencies since it's now memoized


    // Initialize tasks state as empty array
    const [tasks, setTasks] = useState<ScheduleTask[]>([]);

    // Update tasks when batch changes
    useEffect(() => {
        if (batch) {
            setTasks([
                ...(batch.fertilizerScheduleTasks || []),
                ...(batch.pesticideScheduleTasks || []),
            ]);
        }
    }, [batch]);

    if (!batch) {
        console.log("!batch");
        return null;
    }

    const handleTaskCompletion = (taskId: number, isCompleted: boolean) => {
        setTasks((prevTasks) =>
            prevTasks.map((task) =>
                task.id === taskId
                    ? {
                        ...task,
                        isCompleted,
                        taskStatus: isCompleted ? TaskStatus.COMPLETED : new Date(task.taskDate) < new Date() ? TaskStatus.OVERDUE : TaskStatus.IN_PROGRESS,
                        completionDate: isCompleted ? new Date().toISOString() : null,
                    }
                    : task,
            ),
        )
    }

    const handleTaskRemarks = (taskId: number, remarks: string) => {
        setTasks((prevTasks) => prevTasks.map((task) => (task.id === taskId ? {...task, taskRemarks: remarks} : task)))
    }

    const getStatusColor = (status: string) => {
        switch (status) {
            case "COMPLETED":
                return "bg-green-100 text-green-800"
            case "OVERDUE":
                return "bg-red-100 text-red-800"
            case "PENDING":
                return "bg-yellow-100 text-yellow-800"
            default:
                return "bg-gray-100 text-gray-800"
        }
    }

    return (
        <Dialog open={true} onOpenChange={onClose}>
            <DialogContent className="max-w-4xl max-h-[80vh] overflow-y-auto">
                <DialogHeader>
                    <DialogTitle>Crop Batch Details</DialogTitle>
                </DialogHeader>
                <div className="space-y-4">
                    <h3 className="text-lg font-semibold">Crop Information</h3>
                    <p>Crop: {batch.crop.name}</p>
                    <p>Farmer: {`${batch.farmer.firstName} ${batch.farmer.lastName}`}</p>
                    <p>Transplant Date: {new Date(batch.dateOfTransplant).toLocaleDateString()}</p>
                    <p>Location: {batch.location}</p>

                    <h3 className="text-lg font-semibold mt-6">Tasks</h3>
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Type</TableHead>
                                <TableHead>Name</TableHead>
                                <TableHead>Due Date</TableHead>
                                <TableHead>Status</TableHead>
                                <TableHead>Completed</TableHead>
                                <TableHead>Remarks</TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {tasks.map((task) => (
                                <TableRow key={task.id}>
                                    <TableCell>{task.fertilizer ? "Fertilizer" : "Pesticide"}</TableCell>
                                    <TableCell>{task.fertilizer?.name || task.pesticide?.name}</TableCell>
                                    <TableCell>{new Date(task.taskDate).toLocaleDateString()}</TableCell>
                                    <TableCell>
                    <span className={`px-2 py-1 rounded-full text-xs font-semibold ${getStatusColor(task.taskStatus)}`}>
                      {task.taskStatus}
                    </span>
                                    </TableCell>
                                    <TableCell>
                                        <Checkbox
                                            checked={task.isCompleted}
                                            onCheckedChange={(checked) => handleTaskCompletion(task.id, checked as boolean)}
                                        />
                                    </TableCell>
                                    <TableCell>
                                        <Input
                                            value={task.taskRemarks || ""}
                                            onChange={(e) => handleTaskRemarks(task.id, e.target.value)}
                                            placeholder="Add remarks"
                                        />
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                    <div className="flex justify-end">
                        <Button onClick={onClose}>Close</Button>
                    </div>
                </div>
            </DialogContent>
        </Dialog>
    )
}
