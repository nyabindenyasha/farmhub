package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.enums.FertilizerApplicationMethod;
import com.xplug.tech.fertilizer.FertilizerResponse;
import com.xplug.tech.period.PeriodResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class CropFertilizerScheduleResponse {

    private Long id;

//    private CropScheduleResponse cropScheduleResponse;

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
//                .cropScheduleResponse(CropScheduleResponse.of(cropFertilizerSchedule.getCropSchedule()))
                .fertilizer(FertilizerResponse.of(cropFertilizerSchedule.getFertilizer()))
                .stageOfGrowth(PeriodResponse.of(cropFertilizerSchedule.getStageOfGrowth()))
                .applicationInterval(PeriodResponse.of(cropFertilizerSchedule.getApplicationInterval()))
                .rate(cropFertilizerSchedule.getRate())
                .applicationMethod(cropFertilizerSchedule.getApplicationMethod())
                .remarks(cropFertilizerSchedule.getRemarks())
                .build();
    }

}
