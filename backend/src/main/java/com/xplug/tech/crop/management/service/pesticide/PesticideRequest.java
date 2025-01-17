package com.xplug.tech.crop.management.service.pesticide;

import com.xplug.tech.crop.management.enums.PesticideModeOfAction;
import com.xplug.tech.crop.management.enums.PesticideType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
public class PesticideRequest {

    @NotNull(message = "Crop name cannot be null!")
    private String name;

    private String alias;

    private List<String> activeIngredients;

    private String applicationRate;  //pack unit function of pack unit per knapsack

    private Integer safetyInterval; //number of days

    private PesticideType pesticideType;

    private PesticideModeOfAction modeOfAction;

    private List<String> targetPests;

    private List<String> targetDiseases;

}
