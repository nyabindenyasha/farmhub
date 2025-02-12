import React from "react";
import PesticideComponent from "@/othercomponents/pesticide/pesticide";
import {PesticideProvider} from "@/context/PesticideContext";

function Pesticide() {
    return (
        <PesticideProvider>
            <PesticideComponent />
        </PesticideProvider>
    )
}

export default Pesticide
