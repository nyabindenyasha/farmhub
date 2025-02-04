import { CropProvider } from "@/context/CropContext"
import { CropVarietyProvider } from "@/context/CropVarietyContext"
import CreateCropVariety from "@/othercomponents/crop/create-crop-variety";


export default function CropPage() {
    return (
        <CropProvider>
            <CropVarietyProvider>
                <div className="container mx-auto p-4">
                    <h1 className="text-2xl font-bold mb-4">Crop Management</h1>
                    <CreateCropVariety />
                </div>
            </CropVarietyProvider>
        </CropProvider>
    )
}

