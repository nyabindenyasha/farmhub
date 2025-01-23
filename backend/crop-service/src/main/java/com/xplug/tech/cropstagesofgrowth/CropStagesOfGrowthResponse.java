package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.crop.CropResponse;
import com.xplug.tech.crop.CropStagesOfGrowth;
import com.xplug.tech.enums.StageOfGrowth;
import com.xplug.tech.period.PeriodResponse;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class CropStagesOfGrowthResponse {

    private Long id;

    private CropResponse cropResponse;

    private PeriodResponse stageStartDate;

    private PeriodResponse stageEndDate;

    private StageOfGrowth stageOfGrowth;

    public static CropStagesOfGrowthResponse of(CropStagesOfGrowth cropStagesOfGrowth) {
        Objects.requireNonNull(cropStagesOfGrowth, "CropStagesOfGrowth cannot be null!");
        return CropStagesOfGrowthResponse.builder()
                .id(cropStagesOfGrowth.getId())
                .cropResponse(CropResponse.of(cropStagesOfGrowth.getCrop()))
                .stageStartDate(PeriodResponse.of(cropStagesOfGrowth.getStageStartDate()))
                .stageEndDate(PeriodResponse.of(cropStagesOfGrowth.getStageEndDate()))
                .stageOfGrowth(cropStagesOfGrowth.getStageOfGrowth())
                .build();
    }

}
