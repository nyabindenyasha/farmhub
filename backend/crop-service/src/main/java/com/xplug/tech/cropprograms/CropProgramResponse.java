package com.xplug.tech.cropprograms;

//import com.xplug.tech.crop.CropProgram;
//import com.xplug.tech.crop.Period;
//import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleResponse;
//import com.xplug.tech.croppesticideschedule.CropPesticideScheduleResponse;
//import com.xplug.tech.cropschedule.CropScheduleResponse;
//import com.xplug.tech.utils.PeriodUtils;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@ToString
//@Builder
//public class CropProgramResponse {
//
//    private Long id;
//
//    private CropScheduleResponse cropScheduleResponse;
//
//    private List<CropPesticideScheduleResponse> pesticideScheduleList;
//
//    private List<CropFertilizerScheduleResponse> fertilizerScheduleList;
//
//    public static CropProgramResponse of(CropProgram cropProgram) {
//        Objects.requireNonNull(cropProgram, "Crop cannot be null!");
//
//        // Create a comparator for CropPesticideScheduleResponse based on stageOfGrowth
//        Comparator<CropPesticideScheduleResponse> pestStageOfGrowthComparator =
//                (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
//                        Period.builder()
//                                .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
//                                .periodValue(p1.getStageOfGrowth().getPeriodValue())
//                                .build(),
//                        Period.builder()
//                                .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
//                                .periodValue(p2.getStageOfGrowth().getPeriodValue())
//                                .build()
//                );
//
//        Comparator<CropFertilizerScheduleResponse> fertStageOfGrowthComparator =
//                (p1, p2) -> PeriodUtils.PERIOD_COMPARATOR.compare(
//                        Period.builder()
//                                .periodUnit(p1.getStageOfGrowth().getPeriodUnit())
//                                .periodValue(p1.getStageOfGrowth().getPeriodValue())
//                                .build(),
//                        Period.builder()
//                                .periodUnit(p2.getStageOfGrowth().getPeriodUnit())
//                                .periodValue(p2.getStageOfGrowth().getPeriodValue())
//                                .build()
//                );
//
//        // Convert pesticide schedules to list and sort by stageOfGrowth
//        List<CropPesticideScheduleResponse> sortedPesticideList = cropProgram.getCropSchedule().getPesticideScheduleList().stream()
//                .map(CropPesticideScheduleResponse::of)
//                .sorted(pestStageOfGrowthComparator)
//                .collect(Collectors.toList());
//
//        // Convert fertilizer schedules to list
//        List<CropFertilizerScheduleResponse> sortedFertilizerList = cropProgram.getCropSchedule().getFertilizerScheduleList().stream()
//                .map(CropFertilizerScheduleResponse::of)
//                .sorted(fertStageOfGrowthComparator)
//                .collect(Collectors.toList());
//
//        return CropProgramResponse.builder()
//                .id(cropProgram.getId())
//                .cropScheduleResponse(CropScheduleResponse.of(cropProgram.getCropSchedule()))
//                .pesticideScheduleList(sortedPesticideList)
//                .fertilizerScheduleList(sortedFertilizerList)
//                .build();
//    }
//}
