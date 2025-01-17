package com.xplug.tech.crop.management;


import com.xplug.tech.crop.management.cropdata.FertilizerDataLoader;
import com.xplug.tech.crop.management.cropdata.PesticideDataLoader;
import com.xplug.tech.crop.management.utils.AppConstantsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final FertilizerDataLoader fertilizerDataLoader;

    private final PesticideDataLoader pesticideDataLoader;

    public CommandLineAppStartupRunner(FertilizerDataLoader fertilizerDataLoader, PesticideDataLoader pesticideDataLoader) {
        this.fertilizerDataLoader = fertilizerDataLoader;
        this.pesticideDataLoader = pesticideDataLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            AppConstantsConfig.init();
            var fertilizer = fertilizerDataLoader.getByName("Compound C");
            var pesticide = pesticideDataLoader.getByName("lambda");
            log.info("Fertilizer: {}", fertilizer);
            log.info("Pesticide: {}", pesticide);
            log.info("### Application Constants Initialized Successfully!");
        } catch (Exception e) {
            log.info("Exception @ CommandLineRunner: {}", e.getMessage());
        }
    }

}
