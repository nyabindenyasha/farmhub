package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.*;
import com.xplug.tech.cropfarmer.CropFarmerService;
import com.xplug.tech.cropprograms.CropProgramService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public non-sealed class CropBatchMapperImpl implements CropBatchMapper {

    private final CropFarmerService cropFarmerService;

    private final CropProgramService cropProgramService;

    private final CropBatchDao cropBatchRepository;

    private final CropFertilizerScheduleDao cropFertilizerScheduleRepository;

    private final CropPesticideScheduleDao cropPesticideScheduleRepository;

    public CropBatchMapperImpl(CropFarmerService cropFarmerService, CropProgramService cropProgramService, CropBatchDao cropBatchRepository, CropFertilizerScheduleDao cropFertilizerScheduleRepository, CropPesticideScheduleDao cropPesticideScheduleRepository) {
        this.cropFarmerService = cropFarmerService;
        this.cropProgramService = cropProgramService;
        this.cropBatchRepository = cropBatchRepository;
        this.cropFertilizerScheduleRepository = cropFertilizerScheduleRepository;
        this.cropPesticideScheduleRepository = cropPesticideScheduleRepository;
    }

    @Override
    public CropBatch cropBatchFromCropBatchRequest(CropBatchRequest cropBatchRequest) {
        Objects.requireNonNull(cropBatchRequest, "CropBatchRequest cannot be null!");
        CropFarmer cropFarmer = cropFarmerService.getById(cropBatchRequest.getCropFarmerId());
        CropProgram cropProgram = cropProgramService.getById(cropBatchRequest.getCropProgramId());

        Set<CropFertilizerScheduleTask> cropFertilizerScheduleTasks = new HashSet<>();
        Set<CropPesticideScheduleTask> cropPesticideScheduleTasks = new HashSet<>();

        CropBatch cropBatch = CropBatch.builder()
                .cropFarmer(cropFarmer)
                .fertilizerScheduleTasks(cropFertilizerScheduleTasks)
                .pesticideScheduleTasks(cropPesticideScheduleTasks)
                .build();

        var savedCropBatch = cropBatchRepository.save(cropBatch);

        cropProgram.getFertilizerScheduleList().forEach(cropFertilizerSchedule -> {
            CropFertilizerScheduleTask cropFertilizerScheduleTask = CropFertilizerScheduleTask.builder()
                    .cropSchedule(cropFertilizerSchedule.getCropSchedule())
                    .fertilizer(cropFertilizerSchedule.getFertilizer())
                    .stageOfGrowth(cropFertilizerSchedule.getStageOfGrowth())
                    .applicationInterval(cropFertilizerSchedule.getApplicationInterval())
                    .rate(cropFertilizerSchedule.getRate())
                    .applicationMethod(cropFertilizerSchedule.getApplicationMethod())
                    .remarks(cropFertilizerSchedule.getRemarks())
                    .cropBatch(savedCropBatch)
                    .build();
            var savedCropFertilizerScheduleTask = cropFertilizerScheduleRepository.save(cropFertilizerScheduleTask);
            cropFertilizerScheduleTasks.add(savedCropFertilizerScheduleTask);
        });

        cropProgram.getPesticideScheduleList().forEach(cropPesticideSchedule -> {
            CropPesticideScheduleTask cropPesticideScheduleTask = CropPesticideScheduleTask.builder()
                    .cropSchedule(cropPesticideSchedule.getCropSchedule())
                    .pesticide(cropPesticideSchedule.getPesticide())
                    .stageOfGrowth(cropPesticideSchedule.getStageOfGrowth())
                    .applicationInterval(cropPesticideSchedule.getApplicationInterval())
                    .applicationMethod(cropPesticideSchedule.getApplicationMethod())
                    .remarks(cropPesticideSchedule.getRemarks())
                    .cropBatch(savedCropBatch)
                    .build();
            var savedCropPesticideScheduleTask = cropPesticideScheduleRepository.save(cropPesticideScheduleTask);
            cropPesticideScheduleTasks.add(savedCropPesticideScheduleTask);
        });

//        cropBatch.getFertilizerScheduleTasks().addAll(cropFertilizerScheduleTasks);
//        cropBatch.getPesticideScheduleTasks().addAll(cropPesticideScheduleTasks);
//
//        return cropBatchRepository.save(cropBatch);

        return savedCropBatch;

    }

    @Override
    public CropBatch cropBatchFromCropBatchUpdateRequest(CropBatch cropBatch, CropBatchUpdateRequest cropBatchUpdateRequest) {
        Objects.requireNonNull(cropBatch, "CropBatch cannot be null!");
        Objects.requireNonNull(cropBatchUpdateRequest, "CropBatchUpdateRequest cannot be null!");
//        cropBatch.setName(cropBatchUpdateRequest.getName());
//        cropBatch.setFamily(cropBatchUpdateRequest.getFamily());
//        cropBatch.setGenus(cropBatchUpdateRequest.getGenus());
//        cropBatch.setSpecies(cropBatchUpdateRequest.getSpecies());
//        cropBatch.setSubSpecies(cropBatchUpdateRequest.getSubSpecies());
        return cropBatch;
    }

    @Override
    public CropBatchResponse cropBatchResponseFromCropBatch(CropBatch cropBatch) {
        return CropBatchResponse.of(cropBatch);
    }

}
