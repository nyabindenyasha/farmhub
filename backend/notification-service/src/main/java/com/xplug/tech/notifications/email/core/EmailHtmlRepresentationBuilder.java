package com.xplug.tech.notifications.email.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class EmailHtmlRepresentationBuilder {

    private final Configuration freeMarkerConfiguration;

    @Value("${system.name}")
    private String systemName;

    public EmailHtmlRepresentationBuilder(Configuration configuration) {
        this.freeMarkerConfiguration = configuration;
    }

    public String buildHtmlRepresentation(String message, String emailFromHeader) {

        Map<String, Object> model = new ModelMap();

        model.put("message", message);
        model.put("year", systemName + " " + String.valueOf(LocalDate.now().getYear()).replace(",", ""));
        model.put("emailFromHeader", emailFromHeader);
        String htmlString = "";

        try {
            Template template = freeMarkerConfiguration.getTemplate("email-template.ftl");

            htmlString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        } catch (IOException | TemplateException e) {
            log.error("Failed to create an html representation of the email message due to : {}", e.getMessage());
        }

        return htmlString;

    }

}
