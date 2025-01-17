package com.xplug.tech.crop.management.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class AppConstantsConfig {

    public static final String CONTENT_TYPE = "application/json";

    public static String BASE_URL = "http://op-temp.promun.co.zw/api/v1";

    public static String AUTH_TOKEN = "Bearer 20|vYeQecgPDHShkh4noH5Co2HF5gnFst1W4iZPPdEQ9426a6e6";

    public static String CBD = "06-07-2024";

    public static String USD_CREDIT_ACCOUNT = "";

    public static String ZIG_CREDIT_ACCOUNT = "";

    public static void init() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = getEnvSettings();
            prop.load(input);
            CBD = prop.getProperty("CBD");
            BASE_URL = prop.getProperty("BASE_URL");
            AUTH_TOKEN = prop.getProperty("AUTH_TOKEN");
            USD_CREDIT_ACCOUNT = prop.getProperty("USD_CREDIT_ACCOUNT");
            ZIG_CREDIT_ACCOUNT = prop.getProperty("ZIG_CREDIT_ACCOUNT");
        } catch (IOException var11) {
            log.info("IOException @ init() : {}", var11.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var10) {
                    log.info("IOException @ finally : {}", var10.getMessage());
                }
            }
        }

    }

    private static InputStream getEnvSettings() throws FileNotFoundException {
        String path;
        if (getEnvironmentType().equals("WINDOWS")) {
            path = "C:/Users/nyashany/config/axis_integration.ini";
        } else {
            path = System.getProperty("user.home") + File.separator + "configs" + File.separator + "axis_integration.ini";
        }
        return new FileInputStream(path);
    }

    public static String getEnvironmentType() {
        Map<String, String> env = System.getenv();
        return env != null && env.containsKey("COMPUTERNAME") && env.containsKey("USERNAME") ? "WINDOWS" : "LINUX";
    }

}
