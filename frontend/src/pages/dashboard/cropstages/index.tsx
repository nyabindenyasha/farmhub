import React from "react";
import { CropProvider } from "@/context/CropContext";
import CropStagesOfGrowthComponent from "@/othercomponents/cropstages/crop-stages-of-growth";

function CropStagesOfGrowth() {
    return (
        <CropProvider>
            <CropStagesOfGrowthComponent />
        </CropProvider>
    )
}

export default CropStagesOfGrowth
