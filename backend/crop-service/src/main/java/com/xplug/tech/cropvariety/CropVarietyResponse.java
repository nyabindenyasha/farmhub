package com.xplug.tech.cropvariety;

import com.xplug.tech.crop.CropResponse;
import com.xplug.tech.crop.CropVariety;
import com.xplug.tech.enums.VarietyType;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class CropVarietyResponse {

    private Long id;
    private CropResponse crop;
    private String variety;
    private String maturity; //days
    private Integer harvestDuration;
    private String remarks;
    private VarietyType varietyType;

    public static CropVarietyResponse of(CropVariety cropVariety) {
        Objects.requireNonNull(cropVariety, "CropVariety cannot be null!");
        return CropVarietyResponse.builder()
                .id(cropVariety.getId())
                .crop(CropResponse.of(cropVariety.getCrop()))
                .variety(cropVariety.getVariety())
                .maturity(cropVariety.getMaturityStartDay() + "-" + cropVariety.getMaturityEndDay() + " Days")
                .harvestDuration(cropVariety.getHarvestDuration())
                .varietyType(cropVariety.getVarietyType())
                .remarks(cropVariety.getRemarks())
                .build();
    }

}
