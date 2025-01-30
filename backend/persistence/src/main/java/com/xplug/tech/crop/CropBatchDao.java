package com.xplug.tech.crop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropBatchDao extends JpaRepository<CropBatch, Long> {

    List<CropBatch> findByCropFarmerUserAccountId(Long id);

}
