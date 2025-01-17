package com.xplug.tech.crop.management.cropdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xplug.tech.crop.management.service.pesticide.PesticideRequest;
import com.xplug.tech.crop.management.service.pesticide.PesticideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class PesticideDataLoader {

    private final PesticideService pesticideService;

    private List<PesticideRequest> pesticides;

    public PesticideDataLoader(PesticideService pesticideService) {
        this.pesticideService = pesticideService;
    }

    @PostConstruct
    public void loadData() {
        log.info("### PesticideDataLoader");
        try (InputStream inputStream = getClass().getResourceAsStream("/cropdata/pesticide-data.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            pesticides = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            log.info("Loaded pesticide data: " + pesticides.size() + " pesticides.");
            pesticideService.createBulk(pesticides);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load pesticide data", e);
        }
    }

    public List<PesticideRequest> getPesticides() {
        return pesticides;
    }

    public PesticideRequest getByName(String name) {
        return pesticides.stream()
                .filter(pesticide -> pesticide.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
