package com.xplug.tech.crop.management.service.cropfertilizerschedule;

import com.xplug.tech.crop.management.domain.CropFertilizerSchedule;
import com.xplug.tech.crop.management.enums.FertilizerApplicationMethod;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleResponse;
import com.xplug.tech.crop.management.service.fertilizer.FertilizerResponse;
import com.xplug.tech.crop.management.service.period.PeriodResponse;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class CropFertilizerScheduleResponse {

    private Long id;

    private CropScheduleResponse cropScheduleResponse;

    private FertilizerResponse fertilizer;

    private PeriodResponse stageOfGrowth;

    private PeriodResponse applicationInterval;  //period data-type

    private Integer rate;

    private FertilizerApplicationMethod applicationMethod; //enum

    private String remarks;

    public static CropFertilizerScheduleResponse of(CropFertilizerSchedule cropFertilizerSchedule) {
        Objects.requireNonNull(cropFertilizerSchedule, "Crop cannot be null!");
        return CropFertilizerScheduleResponse.builder()
                .id(cropFertilizerSchedule.getId())
                .cropScheduleResponse(CropScheduleResponse.of(cropFertilizerSchedule.getCropSchedule()))
                .fertilizer(FertilizerResponse.of(cropFertilizerSchedule.getFertilizer()))
                .stageOfGrowth(PeriodResponse.of(cropFertilizerSchedule.getStageOfGrowth()))
                .applicationInterval(PeriodResponse.of(cropFertilizerSchedule.getApplicationInterval()))
                .rate(cropFertilizerSchedule.getRate())
                .applicationMethod(cropFertilizerSchedule.getApplicationMethod())
                .remarks(cropFertilizerSchedule.getRemarks())
                .build();
    }

}
