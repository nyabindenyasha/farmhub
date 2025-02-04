import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

interface CropProgramSummaryProps {
    cropProgram: any // Replace 'any' with your actual CropProgram type
}

export function CropProgramSummary({ cropProgram }: CropProgramSummaryProps) {
    return (
        <div className="space-y-6">
            <Card>
                <CardHeader>
                    <CardTitle>Crop Program Details</CardTitle>
                </CardHeader>
                <CardContent>
                    <dl className="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
                        <div className="sm:col-span-1">
                            <dt className="text-sm font-medium text-gray-500">Name</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.name}</dd>
                        </div>
                        <div className="sm:col-span-1">
                            <dt className="text-sm font-medium text-gray-500">Crop ID</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.cropId}</dd>
                        </div>
                        <div className="sm:col-span-1">
                            <dt className="text-sm font-medium text-gray-500">Schedule Type</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.cropScheduleType}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Description</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.description}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Source</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.source}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Remarks</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropProgram.remarks}</dd>
                        </div>
                    </dl>
                </CardContent>
            </Card>

            <Card>
                <CardHeader>
                    <CardTitle>Fertilizer Schedules</CardTitle>
                </CardHeader>
                <CardContent>
                    {cropProgram.fertilizerScheduleRequests.map((schedule: any, index: number) => (
                        <div key={index} className="mb-4 p-4 border rounded-md">
                            <h4 className="text-sm font-medium text-gray-900">Schedule {index + 1}</h4>
                            <dl className="mt-2 grid grid-cols-1 gap-x-4 gap-y-4 sm:grid-cols-2">
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Fertilizer ID</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.fertilizerId}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Application Method</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.applicationMethod}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Rate</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.rate}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Stage of Growth</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{`${schedule.stageOfGrowth.periodValue} ${schedule.stageOfGrowth.periodUnit}`}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Application Interval</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{`${schedule.applicationInterval.periodValue} ${schedule.applicationInterval.periodUnit}`}</dd>
                                </div>
                                <div className="sm:col-span-2">
                                    <dt className="text-sm font-medium text-gray-500">Remarks</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.remarks}</dd>
                                </div>
                            </dl>
                        </div>
                    ))}
                </CardContent>
            </Card>

            <Card>
                <CardHeader>
                    <CardTitle>Pesticide Schedules</CardTitle>
                </CardHeader>
                <CardContent>
                    {cropProgram.pesticideScheduleRequests.map((schedule: any, index: number) => (
                        <div key={index} className="mb-4 p-4 border rounded-md">
                            <h4 className="text-sm font-medium text-gray-900">Schedule {index + 1}</h4>
                            <dl className="mt-2 grid grid-cols-1 gap-x-4 gap-y-4 sm:grid-cols-2">
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Pesticide ID</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.pesticideId}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Application Method</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.applicationMethod}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Stage of Growth</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{`${schedule.stageOfGrowth.periodValue} ${schedule.stageOfGrowth.periodUnit}`}</dd>
                                </div>
                                <div className="sm:col-span-1">
                                    <dt className="text-sm font-medium text-gray-500">Application Interval</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{`${schedule.applicationInterval.periodValue} ${schedule.applicationInterval.periodUnit}`}</dd>
                                </div>
                                <div className="sm:col-span-2">
                                    <dt className="text-sm font-medium text-gray-500">Remarks</dt>
                                    <dd className="mt-1 text-sm text-gray-900">{schedule.remarks}</dd>
                                </div>
                            </dl>
                        </div>
                    ))}
                </CardContent>
            </Card>
        </div>
    )
}

