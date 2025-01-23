package com.xplug.tech;


import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.awt.*;
import java.io.FileOutputStream;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
@EnableJpaAuditing
public class CropManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CropManagementApplication.class, args);

        String outputPath = "test-dir/Cabbage_Production_Guide.pdf";

//        try {
//            // Step 1: Create a Document instance
//            Document document = new Document();
//
//            // Step 2: Create a PdfWriter instance
//            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
//
//            // Step 3: Open the Document
//            document.open();
//
//            // Step 4: Add content
//
//            // Title
//            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD);
//
//            Paragraph title = new Paragraph("Cabbage Production: A Complete Guide", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//
//            document.add(new Paragraph("\n")); // Add a blank line
//
//            // Author
//            Font authorFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.ITALIC);
//            Paragraph author = new Paragraph("Promoting Farming Excellence by Dahlia Shumba", authorFont);
//            author.setAlignment(Element.ALIGN_CENTER);
//            document.add(author);
//
//            document.add(new Paragraph("\n\n")); // Add blank lines
//
//            // Section: Nursery Management
//            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
//            Paragraph nurserySection = new Paragraph("Nursery Management", sectionFont);
//            document.add(nurserySection);
//
//            document.add(new Paragraph("Seeds per gram: 200-350"));
//            document.add(new Paragraph("Emergence time: 5-7 days"));
//            document.add(new Paragraph("Soil temperature: Minimum 4°C, Optimum 18-35°C"));
//
//            document.add(new Paragraph("\n")); // Add a blank line
//
//            // Section: Fertilization
//            Paragraph fertilizationSection = new Paragraph("Fertilization", sectionFont);
//            document.add(fertilizationSection);
//
//            document.add(new Paragraph("Basal Application: Compound C at 30g per plant"));
//            document.add(new Paragraph("Top Dressing: Ammonium Nitrate at 5g per plant"));
//
//            document.add(new Paragraph("\n")); // Add a blank line
//
//            // Table: Dummy Crop Program
//            PdfPTable table = new PdfPTable(5); // 5 columns
//            table.setWidthPercentage(100);
//
//            // Table Headers
//            addTableHeader(table, "Week", "Stage", "Fertilizer", "Pesticides", "Remarks");
//
//            // Table Data (Dummy)
//            addTableRow(table, "1", "Transplanting", "Compound C 30g", "Lambda 16ml", "Use Metolachlor");
//            addTableRow(table, "2", "Vegetative", "Ammonium Nitrate 5g", "Dichlorvos 16ml", "Add Chlorothalonil");
//            addTableRow(table, "3", "Vegetative", "Ammonium Nitrate 5g", "Belt 4ml", "Use Antracol");
//
//            document.add(table);
//
//            // Step 5: Close the Document
//            document.close();
//
//            System.out.println("PDF created successfully: " + outputPath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(header));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        }
    }

    private static void addTableRow(PdfPTable table, String... data) {
        for (String value : data) {
            table.addCell(value);
        }
    }

}
