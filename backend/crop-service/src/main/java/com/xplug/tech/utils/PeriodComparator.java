package com.xplug.tech.utils;

import com.xplug.tech.crop.Period;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleResponse;
import com.xplug.tech.cropscheduletask.CropFertilizerScheduleTaskResponse;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleResponse;
import com.xplug.tech.cropscheduletask.CropPesticideScheduleTaskResponse;

import java.util.Comparator;

public class PeriodComparator {


    // Create a comparator for CropPesticideScheduleResponse based on stageOfGrowth
    public static Comparator<CropPesticideScheduleResponse> pesticideStageOfGrowthComparator =
            (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
                    Period.builder()
                            .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p1.getStageOfGrowth().getPeriodValue())
                            .build(),
                    Period.builder()
                            .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p2.getStageOfGrowth().getPeriodValue())
                            .build()
            );

    public static Comparator<CropPesticideScheduleTaskResponse> pesticideTaskStageOfGrowthComparator =
            (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
                    Period.builder()
                            .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p1.getStageOfGrowth().getPeriodValue())
                            .build(),
                    Period.builder()
                            .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p2.getStageOfGrowth().getPeriodValue())
                            .build()
            );

    public static Comparator<CropFertilizerScheduleResponse> fertilizerStageOfGrowthComparator =
            (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
                    Period.builder()
                            .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p1.getStageOfGrowth().getPeriodValue())
                            .build(),
                    Period.builder()
                            .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p2.getStageOfGrowth().getPeriodValue())
                            .build()
            );

    public static Comparator<CropFertilizerScheduleTaskResponse> fertilizerTaskStageOfGrowthComparator =
            (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
                    Period.builder()
                            .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p1.getStageOfGrowth().getPeriodValue())
                            .build(),
                    Period.builder()
                            .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
                            .periodValue(p2.getStageOfGrowth().getPeriodValue())
                            .build()
            );

}
