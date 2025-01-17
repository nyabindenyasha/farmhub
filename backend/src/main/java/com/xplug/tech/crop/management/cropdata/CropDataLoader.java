package com.xplug.tech.crop.management.cropdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class CropDataLoader {

    private List<CropData> cropDataList;

    private final CropInitializerService cropInitializerService;

    public CropDataLoader(CropInitializerService cropInitializerService) {
        this.cropInitializerService = cropInitializerService;
    }

    @PostConstruct
    public void loadData() {
        log.info("### CropDataLoader");
        try (InputStream inputStream = getClass().getResourceAsStream("/cropdata/crop-data.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            cropDataList = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            log.info("Loaded crop data: " + cropDataList.size() + " crops.");
            log.info("CropData {}", cropDataList);
            cropInitializerService.initializeCrops(cropDataList);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fertilizer data", e);
        }
    }

    public List<CropData> getCropData() {
        return cropDataList;
    }

}
