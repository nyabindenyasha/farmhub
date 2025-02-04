import React from "react";
import CropComponent from "@/othercomponents/crop/crop";
import { CropProvider } from "@/context/CropContext";
import {CropProgramProvider} from "@/context/CropProgramContext";
import CropProgramComponent from "@/othercomponents/cropprogram/crop-program";

function Crop() {
    return (
        <CropProgramProvider>
            <CropProgramComponent />
        </CropProgramProvider>
    )
}

export default Crop
