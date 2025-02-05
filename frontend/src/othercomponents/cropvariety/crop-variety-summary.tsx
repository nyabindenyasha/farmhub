import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card"
import {CropVarietyRequest} from "@/othercomponents/cropvariety/create-crop-variety";

interface CropVarietySummaryProps {
    cropVariety: CropVarietyRequest // Replace 'any' with your actual CropVariety type
}

export function CropVarietySummary({cropVariety}: CropVarietySummaryProps) {
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
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.variety}</dd>
                        </div>
                        <div className="sm:col-span-1">
                            <dt className="text-sm font-medium text-gray-500">Crop ID</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.cropId}</dd>
                        </div>
                        <div className="sm:col-span-1">
                            <dt className="text-sm font-medium text-gray-500">Maturity Start Day</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.maturityStartDay}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Maturity End Day</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.maturityEndDay}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Harvest Duration (Days)</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.harvestDuration}</dd>
                        </div>
                        <div className="sm:col-span-2">
                            <dt className="text-sm font-medium text-gray-500">Remarks</dt>
                            <dd className="mt-1 text-sm text-gray-900">{cropVariety.remarks}</dd>
                        </div>
                    </dl>
                </CardContent>
            </Card>
        </div>
    )
}

