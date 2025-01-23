package com.xplug.tech.fertilizer;

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

    private String remarks;

}
