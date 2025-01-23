package com.xplug.tech.pesticide;

import com.xplug.tech.crop.Pesticide;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class PesticideMapperImpl implements PesticideMapper {

    @Override
    public Pesticide pesticideFromPesticideRequest(PesticideRequest pesticideRequest) {
        Objects.requireNonNull(pesticideRequest, "PesticideRequest cannot be null!");
        return Pesticide.builder()
                .name(pesticideRequest.getName())
                .alias(pesticideRequest.getAlias())
                .activeIngredients(pesticideRequest.getActiveIngredients())
                .pesticideType(pesticideRequest.getPesticideType())
                .modeOfAction(pesticideRequest.getModeOfAction())
                .safetyInterval(pesticideRequest.getSafetyInterval())
                .applicationRate(pesticideRequest.getApplicationRate())
                .targetPests(pesticideRequest.getTargetPests())
                .targetDiseases(pesticideRequest.getTargetDiseases())
                .build();
    }

    @Override
    public Pesticide pesticideFromPesticideUpdateRequest(Pesticide pesticide, PesticideUpdateRequest pesticideUpdateRequest) {
        Objects.requireNonNull(pesticide, "Pesticide cannot be null!");
        Objects.requireNonNull(pesticideUpdateRequest, "PesticideUpdateRequest cannot be null!");
        pesticide.setName(pesticideUpdateRequest.getName());
        pesticide.setAlias(pesticideUpdateRequest.getAlias());
        pesticide.setActiveIngredients(pesticideUpdateRequest.getActiveIngredients());
        pesticide.setPesticideType(pesticideUpdateRequest.getPesticideType());
        pesticide.setModeOfAction(pesticideUpdateRequest.getModeOfAction());
        pesticide.setSafetyInterval(pesticideUpdateRequest.getSafetyInterval());
        pesticide.setApplicationRate(pesticideUpdateRequest.getApplicationRate());
        pesticide.setTargetPests(pesticideUpdateRequest.getTargetPests());
        pesticide.setTargetDiseases(pesticideUpdateRequest.getTargetDiseases());
        return pesticide;
    }

    @Override
    public PesticideResponse pesticideResponseFromPesticide(Pesticide pesticide) {
        return PesticideResponse.of(pesticide);
    }

}
