import React from "react";
import {CropProgramProvider} from "@/context/CropProgramContext";
import CropProgramComponent from "@/farmercomponents/cropprogram/crop-program";

function CropBatch() {
    return (
        <CropProgramProvider>
            <CropProgramComponent/>
        </CropProgramProvider>
    )
}

export default CropBatch
