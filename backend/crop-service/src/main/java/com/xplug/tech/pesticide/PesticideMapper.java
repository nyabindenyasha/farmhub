package com.xplug.tech.pesticide;

import com.xplug.tech.crop.Pesticide;

public sealed interface PesticideMapper permits PesticideMapperImpl {

    Pesticide pesticideFromPesticideRequest(PesticideRequest pesticideRequest);

    Pesticide pesticideFromPesticideUpdateRequest(Pesticide pesticide, PesticideUpdateRequest pesticideUpdateRequest);

    PesticideResponse pesticideResponseFromPesticide(Pesticide pesticide);

}
