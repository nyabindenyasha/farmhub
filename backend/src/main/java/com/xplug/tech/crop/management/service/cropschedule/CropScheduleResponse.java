package com.xplug.tech.crop.management.service.cropschedule;

import com.xplug.tech.crop.management.domain.CropSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import com.xplug.tech.crop.management.service.crop.CropResponse;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    public static CropScheduleResponse of(CropSchedule cropSchedule) {
        Objects.requireNonNull(cropSchedule, "Crop cannot be null!");
        return CropScheduleResponse.builder()
                .id(cropSchedule.getId())
                .cropResponse(CropResponse.of(cropSchedule.getCrop()))
                .name(cropSchedule.getName())
                .description(cropSchedule.getDescription())
                .source(cropSchedule.getSource())
                .remarks(cropSchedule.getRemarks())
                .cropScheduleType(cropSchedule.getCropScheduleType())
                .build();
    }

}
