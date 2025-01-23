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


//@Slf4j
//@Component
//public class FertilizerDataLoader {
//
//    private final FertilizerService fertilizerService;
//
//    private List<FertilizerRequest> fertilizers;
//
//    public FertilizerDataLoader(FertilizerService fertilizerService) {
//        this.fertilizerService = fertilizerService;
//    }
//
//    @PostConstruct
//    public void loadData() {
//        log.info("### FertilizerDataLoader - Starting to load data");
//
//        // Try different class loaders to help diagnose the issue
//        ClassLoader classLoader = getClass().getClassLoader();
//        log.info("Class loader: " + classLoader);
//
//        String resourcePath = "/cropdata/fertilizer-data.json";
//        log.info("Attempting to load resource from path: " + resourcePath);
//
//        // Try multiple ways to load the resource and store the final result
//        InputStream resourceStream = getClass().getResourceAsStream(resourcePath);
//        if (resourceStream == null) {
//            log.info("Trying with class loader directly");
//            resourceStream = classLoader.getResourceAsStream(resourcePath.substring(1)); // Remove leading slash for classloader
//        }
//
//        if (resourceStream == null) {
//            log.error("Could not find resource at path: " + resourcePath);
//            throw new RuntimeException("Resource not found: " + resourcePath);
//        }
//
//        try (InputStream inputStream = resourceStream) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            fertilizers = objectMapper.readValue(inputStream, new TypeReference<>() {});
//            log.info("Successfully loaded fertilizer data: " + fertilizers.size() + " fertilizers.");
//            fertilizerService.createBulk(fertilizers);
//        } catch (Exception e) {
//            log.error("Failed to load fertilizer data", e);
//            throw new RuntimeException("Failed to load fertilizer data", e);
//        }
//    }
//
//    public List<FertilizerRequest> getFertilizers() {
//        return fertilizers;
//    }
//
//    public FertilizerRequest getByName(String name) {
//        return fertilizers.stream()
//                .filter(fertilizer -> fertilizer.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
//    }
//}


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

    @PostConstruct
    public void loadData2() {
        try (InputStream inputStream = getClass().getResourceAsStream("/cropdata/fertilizer-datas.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            FertilizerData fertilizerData = objectMapper.readValue(inputStream, FertilizerData.class);
            fertilizers = fertilizerData.getFertilizers();
            log.info("Loaded fertilizer data: " + fertilizers.size() + " fertilizers.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fertilizer data", e);
        }
    }

    public List<FertilizerRequest> getFertilizers() {
        return fertilizers;
    }

    public FertilizerRequest getByName(String name) {
        return fertilizers.stream()
                .filter(fertilizer -> fertilizer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
