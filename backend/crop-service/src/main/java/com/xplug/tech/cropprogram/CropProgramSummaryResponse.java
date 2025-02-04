package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.enums.CropScheduleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class CropProgramSummaryResponse {

    private Long id;

//    private CropResponse cropResponse;

    @NotNull(message = "Crop Schedule name cannot be null!")
    private String name;

    private String description;

    private String source; //agronomist/company

    private String remarks;

    private CropScheduleType cropScheduleType;

    public static CropProgramSummaryResponse of(CropProgram cropProgram) {
        Objects.requireNonNull(cropProgram, "CropSchedule cannot be null!");

        return CropProgramSummaryResponse.builder()
                .id(cropProgram.getId())
//                .cropResponse(CropResponse.of(cropSchedule.getCrop()))
                .name(cropProgram.getName())
                .description(cropProgram.getDescription())
                .source(cropProgram.getSource())
                .remarks(cropProgram.getRemarks())
                .cropScheduleType(cropProgram.getCropScheduleType())
                .build();
    }

}


