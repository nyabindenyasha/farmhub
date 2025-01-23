package com.xplug.tech.crop;

import java.util.List;

public sealed interface CropService permits CropServiceImpl {

    List<Crop> getAll();

    Crop getById(Long id);

    Crop getByName(String name);

    Crop create(CropRequest cropRequest);

    Crop initialize(CropRequest cropRequest);

    Crop update(CropUpdateRequest cropUpdateRequest);

    void delete(Long id);

}
