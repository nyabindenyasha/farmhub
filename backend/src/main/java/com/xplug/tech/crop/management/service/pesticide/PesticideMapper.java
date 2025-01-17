package com.xplug.tech.crop.management.service.pesticide;

import com.xplug.tech.crop.management.domain.Pesticide;

public sealed interface PesticideMapper permits PesticideMapperImpl {

    Pesticide pesticideFromPesticideRequest(PesticideRequest pesticideRequest);

    Pesticide pesticideFromPesticideUpdateRequest(Pesticide pesticide, PesticideUpdateRequest pesticideUpdateRequest);

    PesticideResponse pesticideResponseFromPesticide(Pesticide pesticide);

}
