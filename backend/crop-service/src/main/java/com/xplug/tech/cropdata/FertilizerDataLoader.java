package com.xplug.tech.cropdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xplug.tech.fertilizer.FertilizerRequest;
import com.xplug.tech.fertilizer.FertilizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class FertilizerDataLoader {

    private final FertilizerService fertilizerService;

    private List<FertilizerRequest> fertilizers;

    public FertilizerDataLoader(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @PostConstruct
    public void loadData() {
        log.info("### FertilizerDataLoader");
        try (InputStream inputStream = getClass().getResourceAsStream("/cropdata/fertilizer-data.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            fertilizers = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            log.info("Loaded fertilizer data: " + fertilizers.size() + " fertilizers.");
            fertilizerService.createBulk(fertilizers);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fertilizer data", e);
        }
    }

    public FertilizerRequest getByName(String name) {
        return fertilizers.stream()
                .filter(fertilizer -> fertilizer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
