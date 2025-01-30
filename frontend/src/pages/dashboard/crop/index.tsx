import React from "react";
import CropComponent from "@/othercomponents/crop/crop";
import { CropProvider } from "@/context/CropContext";

function Crop() {
    return (
        <CropProvider>
            <CropComponent />
        </CropProvider>
    )
}

export default Crop
