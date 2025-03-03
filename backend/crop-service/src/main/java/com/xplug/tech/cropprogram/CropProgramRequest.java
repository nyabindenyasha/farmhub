package com.xplug.tech.cropprogram;

import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;
import com.xplug.tech.enums.CropScheduleType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
public class CropProgramRequest {

    @NotNull(message = "Crop Id cannot be null!")
    private Long cropId;

    @NotNull(message = "Crop Schedule name cannot be null!")
    private String name;

    private String description;

    private String source; //agronomist/company

    private String remarks;

    private CropScheduleType cropScheduleType;

    private List<CropFertilizerScheduleRequest> fertilizerScheduleRequests;

    private List<CropPesticideScheduleRequest> pesticideScheduleRequests;

}
