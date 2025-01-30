package com.xplug.tech.cropprograms;

import com.xplug.tech.crop.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropProgramServiceImpl implements CropProgramService {

    private final CropProgramDao cropProgramDao;

    public CropProgramServiceImpl(CropProgramDao cropProgramDao) {
        this.cropProgramDao = cropProgramDao;
    }

    @Override
    public List<CropProgram> getAll() {
        return cropProgramDao.findAll();
    }

    @Override
    public CropProgram getById(Long id) {
        return cropProgramDao.findById(id)
                .orElseThrow(() -> new RuntimeException("CropProgram not found with id: " + id));
    }

    @Override
    public CropProgram getByCropScheduleId(Long cropScheduleId) {
        return cropProgramDao.findByCropScheduleId(cropScheduleId)
                .orElseThrow(() -> new RuntimeException("CropProgram not found with cropScheduleId: " + cropScheduleId));
    }

    @Override
    public CropProgram create(CropSchedule cropSchedule) {
        CropProgram cropProgram = new CropProgram();
        cropProgram.setCropSchedule(cropSchedule);
        return cropProgramDao.save(cropProgram);
    }

    @Override
    public CropProgram updateFertilizerSchedule(CropFertilizerSchedule cropFertilizerSchedule) {
        CropProgram cropProgram = getByCropScheduleId(cropFertilizerSchedule.getCropSchedule().getId());
        cropProgram.getFertilizerScheduleList().add(cropFertilizerSchedule);
        return cropProgramDao.save(cropProgram);
    }

    @Override
    public CropProgram updatePesticideSchedule(CropPesticideSchedule cropPesticideSchedule) {
        CropProgram cropProgram = getByCropScheduleId(cropPesticideSchedule.getCropSchedule().getId());
        cropProgram.getPesticideScheduleList().add(cropPesticideSchedule);
        return cropProgramDao.save(cropProgram);
    }

}
