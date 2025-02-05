import {CropSelector} from "@/othercomponents/shared/crop-selector";
import {CropProvider} from "@/context/CropContext";

export default function TestCropSelector() {
    return (
        <main className="flex min-h-screen flex-col items-center justify-center p-24">
            <div className="w-full max-w-sm">
                <h1 className="text-2xl font-bold mb-4">Crop Selector</h1>
                <CropProvider>
                    <CropSelector/>
                </CropProvider>
            </div>
        </main>
    )
}
