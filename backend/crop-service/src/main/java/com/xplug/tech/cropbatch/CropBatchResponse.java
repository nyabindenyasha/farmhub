package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropResponse;
import com.xplug.tech.cropfarmer.CropFarmerSummaryResponse;
import com.xplug.tech.cropprogram.CropProgramSummaryResponse;
import com.xplug.tech.cropscheduletask.CropFertilizerScheduleTaskResponse;
import com.xplug.tech.cropscheduletask.CropPesticideScheduleTaskResponse;
import com.xplug.tech.usermanager.user.UserAccountResponse;
import com.xplug.tech.utils.PeriodComparator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
public class CropBatchResponse {

    private Long id;

    private CropResponse crop;

    private UserAccountResponse farmer; //farmer

    private CropProgramSummaryResponse cropSchedule;

    private LocalDateTime dateOfTransplant; //to derive crop stages and day of maturity
    private String location;
    private String remarks;

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
                .crop(CropResponse.of(cropBatch.getCropFarmer().getCrop()))
                .farmer(UserAccountResponse.of(cropBatch.getCropFarmer().getUserAccount()))
                .cropSchedule(CropProgramSummaryResponse.of(cropBatch.getCropFarmer().getCropProgram()))
                .dateOfTransplant(cropBatch.getCropFarmer().getDateOfTransplant())
                .location(cropBatch.getCropFarmer().getLocation())
                .remarks(cropBatch.getCropFarmer().getRemarks())
                .pesticideScheduleTasks(sortedPesticideTaskList)
                .fertilizerScheduleTasks(sortedFertilizerTaskList)
                .build();
    }

}
