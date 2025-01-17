package com.xplug.tech.crop.management.service.pesticide;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PesticideUpdateRequest extends PesticideRequest {

    @NotNull(message = "Pesticide id cannot be null!")
    private Long id;

}
