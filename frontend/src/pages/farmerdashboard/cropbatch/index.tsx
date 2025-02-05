import React from "react";
import {CropBatchProvider} from "@/context/CropBatchContext";
import CropBatchComponent from "@/farmercomponents/cropbatches/crop-batch";

function CropBatch() {
    return (
        <CropBatchProvider>
            <CropBatchComponent />
        </CropBatchProvider>
    )
}

export default CropBatch
