package com.xplug.tech.crop;

import com.xplug.tech.cropprograms.CropProgramPDFGenerator;
import com.xplug.tech.cropprograms.CropProgramPDFGeneratorTest;
import com.xplug.tech.cropprograms.CropProgramResponse;
import com.xplug.tech.cropprograms.CropProgramService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-program")
public class CropProgramController {

    private final CropProgramService cropProgramService;

    private final CropProgramPDFGenerator cropProgramPDFGenerator;

    private final CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest;

    public CropProgramController(CropProgramService cropProgramService, CropProgramPDFGenerator cropProgramPDFGenerator, CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest) {
        this.cropProgramService = cropProgramService;
        this.cropProgramPDFGenerator = cropProgramPDFGenerator;
        this.cropProgramPDFGeneratorTest = cropProgramPDFGeneratorTest;
    }

    @GetMapping
    @Operation(summary = "Get All Crop Programs")
    public List<CropProgramResponse> getAll() {
        return cropProgramService.getAll().stream().map(CropProgramResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/crop-schedule/{cropScheduleId}")
    @Operation(summary = "Get Crop Program By Crop Schedule Id")
    public CropProgram getByCropScheduleId(@PathVariable Long cropScheduleId) {
        return cropProgramService.getByCropScheduleId(cropScheduleId);
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

    @GetMapping("/pdf/test/{cropId}")
    public ResponseEntity<byte[]> generateCabbageProgramPdfTest(@PathVariable Long cropId) {
        byte[] pdfBytes = cropProgramPDFGeneratorTest.generateCabbageProgramPdf(cropId);

        var cropName = cropProgramPDFGenerator.getCropName(cropId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + cropName + "_Program.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
