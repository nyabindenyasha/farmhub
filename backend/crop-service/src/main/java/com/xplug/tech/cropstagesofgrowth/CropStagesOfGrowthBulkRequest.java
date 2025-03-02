package com.xplug.tech.cropstagesofgrowth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class CropStagesOfGrowthBulkRequest {

    @NotNull(message = "Crop Id name cannot be null!")
    private Long cropId;

    private List<CropStagesOfGrowthRequest> cropStages;

}
