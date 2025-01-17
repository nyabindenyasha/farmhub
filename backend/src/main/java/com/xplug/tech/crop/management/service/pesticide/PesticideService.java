package com.xplug.tech.crop.management.service.pesticide;

import com.xplug.tech.crop.management.domain.Pesticide;

import java.util.List;

public sealed interface PesticideService permits PesticideServiceImpl {

    List<Pesticide> getAll();

    Pesticide getById(Long id);

    Pesticide getByName(String name);

    Pesticide create(PesticideRequest pesticideRequest);

    Pesticide initialize(PesticideRequest pesticideRequest);

    List<Pesticide> createBulk(List<PesticideRequest> pesticideRequests);

    Pesticide update(PesticideUpdateRequest pesticideUpdateRequest);

    void delete(Long id);

}
