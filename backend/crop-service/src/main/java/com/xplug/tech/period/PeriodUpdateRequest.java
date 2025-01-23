package com.xplug.tech.period;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PeriodUpdateRequest extends PeriodRequest {

    @NotNull(message = "Period id cannot be null!")
    private Long id;

}
