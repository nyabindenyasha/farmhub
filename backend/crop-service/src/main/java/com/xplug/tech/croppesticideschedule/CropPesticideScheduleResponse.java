package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.cropschedule.CropScheduleResponse;
import com.xplug.tech.enums.PesticideApplicationMethod;
import com.xplug.tech.period.PeriodResponse;
import com.xplug.tech.pesticide.PesticideResponse;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class CropPesticideScheduleResponse {

    private Long id;

    private CropScheduleResponse cropScheduleResponse;

    private PesticideResponse pesticide;

    private PeriodResponse stageOfGrowth;

    private PeriodResponse applicationInterval;  //period data-type

    private PesticideApplicationMethod applicationMethod; //enum

    private String remarks;

    public static CropPesticideScheduleResponse of(CropPesticideSchedule cropPesticideSchedule) {
        Objects.requireNonNull(cropPesticideSchedule, "Crop cannot be null!");
        return CropPesticideScheduleResponse.builder()
                .id(cropPesticideSchedule.getId())
                .cropScheduleResponse(CropScheduleResponse.of(cropPesticideSchedule.getCropSchedule()))
                .pesticide(PesticideResponse.of(cropPesticideSchedule.getPesticide()))
                .stageOfGrowth(PeriodResponse.of(cropPesticideSchedule.getStageOfGrowth()))
                .applicationInterval(PeriodResponse.of(cropPesticideSchedule.getApplicationInterval()))
                .applicationMethod(cropPesticideSchedule.getApplicationMethod())
                .remarks(cropPesticideSchedule.getRemarks())
                .build();
    }

}
