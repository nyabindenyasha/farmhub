package com.xplug.tech.crop.management.api;

import com.xplug.tech.crop.management.service.cropprograms.CropProgramPDFGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-program")
public class CropProgramController {

    private final CropProgramPDFGenerator cropProgramPDFGenerator;

    public CropProgramController(CropProgramPDFGenerator cropProgramPDFGenerator) {
        this.cropProgramPDFGenerator = cropProgramPDFGenerator;
    }

    @GetMapping("/pdf/{cropId}")
    public ResponseEntity<byte[]> generateCabbageProgramPdf(@PathVariable Long cropId) {
        byte[] pdfBytes = cropProgramPDFGenerator.generateCabbageProgramPdf(cropId);

        var cropName = cropProgramPDFGenerator.getCropName(cropId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + cropName + "_Program.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
