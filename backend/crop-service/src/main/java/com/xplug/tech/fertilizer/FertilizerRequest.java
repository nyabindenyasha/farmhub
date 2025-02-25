package com.xplug.tech.fertilizer;

import com.xplug.tech.enums.FertilizerType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class FertilizerRequest {

    @NotNull(message = "Fertilizer name cannot be null!")
    private String name;

    private String alias;

    private String composition;

    private FertilizerType type;

    private String remarks;

}
