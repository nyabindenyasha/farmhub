package com.xplug.tech.cropschedule;

import com.xplug.tech.crop.CropResponse;
import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.crop.Period;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleResponse;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleResponse;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.utils.PeriodUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
public class CropScheduleResponse {

    private Long id;

    private CropResponse cropResponse;

    @NotNull(message = "Crop Schedule name cannot be null!")
    private String name;

    private String description;

    private String source; //agronomist/company

    private String remarks;

    private CropScheduleType cropScheduleType;

    private List<CropPesticideScheduleResponse> pesticideScheduleList;

    private List<CropFertilizerScheduleResponse> fertilizerScheduleList;

    public static CropScheduleResponse of(CropSchedule cropSchedule) {
        Objects.requireNonNull(cropSchedule, "CropSchedule cannot be null!");

        // Create a comparator for CropPesticideScheduleResponse based on stageOfGrowth
        Comparator<CropPesticideScheduleResponse> pestStageOfGrowthComparator =
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

        Comparator<CropFertilizerScheduleResponse> fertStageOfGrowthComparator =
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

        // Convert pesticide schedules to list and sort by stageOfGrowth
        List<CropPesticideScheduleResponse> sortedPesticideList = cropSchedule.getPesticideScheduleList().stream()
                .map(CropPesticideScheduleResponse::of)
                .sorted(pestStageOfGrowthComparator)
                .collect(Collectors.toList());

        // Convert fertilizer schedules to list
        List<CropFertilizerScheduleResponse> sortedFertilizerList = cropSchedule.getFertilizerScheduleList().stream()
                .map(CropFertilizerScheduleResponse::of)
                .sorted(fertStageOfGrowthComparator)
                .collect(Collectors.toList());

        return CropScheduleResponse.builder()
                .id(cropSchedule.getId())
                .cropResponse(CropResponse.of(cropSchedule.getCrop()))
                .name(cropSchedule.getName())
                .description(cropSchedule.getDescription())
                .source(cropSchedule.getSource())
                .remarks(cropSchedule.getRemarks())
                .cropScheduleType(cropSchedule.getCropScheduleType())
                .pesticideScheduleList(sortedPesticideList)
                .fertilizerScheduleList(sortedFertilizerList)
                .build();
    }

}


