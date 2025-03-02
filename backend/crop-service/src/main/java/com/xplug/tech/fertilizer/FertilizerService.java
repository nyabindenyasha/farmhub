package com.xplug.tech.fertilizer;

import com.xplug.tech.crop.Fertilizer;

import java.util.List;

public sealed interface FertilizerService permits FertilizerServiceImpl {

    List<Fertilizer> getAll();

    Fertilizer getById(Long id);

    Fertilizer getByName(String name);

    Fertilizer create(FertilizerRequest fertilizerRequest);

    Fertilizer initialize(FertilizerRequest fertilizerRequest);

    List<Fertilizer> createBulk(List<FertilizerRequest> fertilizerRequests);

    Fertilizer update(FertilizerUpdateRequest fertilizerUpdateRequest);

    void delete(Long id);

}
