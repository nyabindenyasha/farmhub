package com.xplug.tech.crop.management.service.fertilizer;

import com.xplug.tech.crop.management.domain.Fertilizer;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class FertilizerResponse {

    private Long id;
    private String name;
    private String alias;
    private String composition;
    private String remarks;

    public static FertilizerResponse of(Fertilizer fertilizer) {
        Objects.requireNonNull(fertilizer, "Fertilizer cannot be null!");
        return FertilizerResponse.builder()
                .id(fertilizer.getId())
                .name(fertilizer.getName())
                .alias(fertilizer.getAlias())
                .composition(fertilizer.getComposition())
                .remarks(fertilizer.getRemarks())
                .build();
    }

}
