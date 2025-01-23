package com.xplug.tech.period;

import com.xplug.tech.enums.PeriodUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PeriodRequest {

    @NotNull(message = "Period unit cannot be null!")
    private PeriodUnit periodUnit;

    @NotNull(message = "Period value cannot be null!")
    private Integer periodValue;

}
