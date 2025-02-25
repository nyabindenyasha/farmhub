package com.xplug.tech.crop;

import com.xplug.tech.crop.data.CropData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropDataDao extends JpaRepository<CropData, Long> {

    Optional<CropData> findByClassificationId(Long cropId);

}
