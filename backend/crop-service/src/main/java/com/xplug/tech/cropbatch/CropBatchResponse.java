package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.cropfarmer.CropFarmerSummaryResponse;
import com.xplug.tech.cropscheduletask.CropFertilizerScheduleTaskResponse;
import com.xplug.tech.cropscheduletask.CropPesticideScheduleTaskResponse;
import com.xplug.tech.utils.PeriodComparator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
public class CropBatchResponse {

    private Long id;

    private CropFarmerSummaryResponse cropFarmerResponse;

    private List<CropFertilizerScheduleTaskResponse> fertilizerScheduleTasks;


    private List<CropPesticideScheduleTaskResponse> pesticideScheduleTasks;

    public static CropBatchResponse of(CropBatch cropBatch) {
        Objects.requireNonNull(cropBatch, "Crop Batch cannot be null!");

        // Convert pesticide schedules to list and sort by stageOfGrowth
        List<CropPesticideScheduleTaskResponse> sortedPesticideTaskList = cropBatch.getPesticideScheduleTasks().stream()
                .map(CropPesticideScheduleTaskResponse::of)
                .sorted(PeriodComparator.pesticideTaskStageOfGrowthComparator)
                .collect(Collectors.toList());

        // Convert fertilizer schedules to list
        List<CropFertilizerScheduleTaskResponse> sortedFertilizerTaskList = cropBatch.getFertilizerScheduleTasks().stream()
                .map(CropFertilizerScheduleTaskResponse::of)
                .sorted(PeriodComparator.fertilizerTaskStageOfGrowthComparator)
                .collect(Collectors.toList());

        return CropBatchResponse.builder()
                .id(cropBatch.getId())
                .cropFarmerResponse(CropFarmerSummaryResponse.of(cropBatch.getCropFarmer()))
                .pesticideScheduleTasks(sortedPesticideTaskList)
                .fertilizerScheduleTasks(sortedFertilizerTaskList)
                .build();
    }

}
