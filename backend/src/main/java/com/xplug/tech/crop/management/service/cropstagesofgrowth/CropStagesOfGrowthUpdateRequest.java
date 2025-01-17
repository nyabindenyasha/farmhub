package com.xplug.tech.crop.management.service.cropstagesofgrowth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropStagesOfGrowthUpdateRequest extends CropStagesOfGrowthRequest {

    @NotNull(message = "CropStagesOfGrowth Id cannot be null!")
    private Long id;

}
