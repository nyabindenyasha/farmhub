package com.xplug.tech.pesticide;

import com.xplug.tech.crop.Pesticide;
import com.xplug.tech.enums.PesticideModeOfAction;
import com.xplug.tech.enums.PesticideType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class PesticideResponse {

    private Long id;
    private String name;
    private String alias;
    private List<String> activeIngredients;
    private String applicationRate;  //pack unit function of pack unit per knapsack
    private Integer safetyInterval;
    private PesticideType pesticideType;
    private PesticideModeOfAction modeOfAction;
    private List<String> targetPests;
    private List<String> targetDiseases;

    //todo
    private List<PesticideResponse> alternatives;

    public static PesticideResponse of(Pesticide pesticide) {
        Objects.requireNonNull(pesticide, "Pesticide cannot be null!");
        return PesticideResponse.builder()
                .id(pesticide.getId())
                .name(pesticide.getName())
                .alias(pesticide.getAlias())
                .activeIngredients(pesticide.getActiveIngredients())
                .applicationRate(pesticide.getApplicationRate())
                .safetyInterval(pesticide.getSafetyInterval())
                .pesticideType(pesticide.getPesticideType())
                .modeOfAction(pesticide.getModeOfAction())
                .targetPests(pesticide.getTargetPests())
                .targetDiseases(pesticide.getTargetDiseases())
                .build();
    }

}
