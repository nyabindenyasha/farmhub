package com.xplug.tech.cropdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@Slf4j
//@Component
public class CropDataLoader {

    private List<CropData> cropDataList;

    private final CropInitializerService cropInitializerService;

    private final ObjectMapper objectMapper;
    @Value("${crop.path}")
    private String cropPath;

    public CropDataLoader(CropInitializerService cropInitializerService, ObjectMapper objectMapper) {
        this.cropInitializerService = cropInitializerService;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadData() {
        log.info("### CropDataLoader");

        File dir = new File("/Users/nyashan/FARMHUB/farmhub/farmhub-next-js-template/backend/app/src/main/resources/crops");

        File[] directoryListing = dir.listFiles();

        log.info("### directoryListing: {}", (Object) directoryListing);

        if (directoryListing != null) {
            for (File child : directoryListing) {
                log.info("### child: {}", child);
                try {
                    cropDataList = objectMapper.readValue(child, new TypeReference<>() {
                    });
                    log.info("Loaded crop data: " + cropDataList.size() + " crops.");
                    log.info("CropData {}", cropDataList);
                    cropInitializerService.initializeCrops(cropDataList);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to load fertilizer data", e);
                }
            }
        }

//        try (InputStream inputStream = getClass().getResourceAsStream("/cropdata/crop-data.json")) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            cropDataList = objectMapper.readValue(inputStream, new TypeReference<>() {
//            });
//            log.info("Loaded crop data: " + cropDataList.size() + " crops.");
//            log.info("CropData {}", cropDataList);
//            cropInitializerService.initializeCrops(cropDataList);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load fertilizer data", e);
//        }

    }

}
