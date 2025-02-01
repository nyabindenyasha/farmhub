package com.xplug.tech.crop;

import com.xplug.tech.cropprograms.CropProgramPDFGenerator;
import com.xplug.tech.cropprograms.CropProgramPDFGeneratorTest;
import com.xplug.tech.cropschedule.CropScheduleResponse;
import com.xplug.tech.cropschedule.CropScheduleService;
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

    private final CropScheduleService cropScheduleService;

    private final CropProgramPDFGenerator cropProgramPDFGenerator;

    private final CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest;

    public CropProgramController(CropScheduleService cropScheduleService, CropProgramPDFGenerator cropProgramPDFGenerator, CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest) {
        this.cropScheduleService = cropScheduleService;
        this.cropProgramPDFGenerator = cropProgramPDFGenerator;
        this.cropProgramPDFGeneratorTest = cropProgramPDFGeneratorTest;
    }

    @GetMapping
    @Operation(summary = "Get All Crop Programs")
    public List<CropScheduleResponse> getAll() {
        return cropScheduleService.getAll().stream().map(CropScheduleResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/crop-schedule/{cropScheduleId}")
    @Operation(summary = "Get Crop Program By Crop Schedule Id")
    public CropScheduleResponse getByCropScheduleId(@PathVariable Long cropScheduleId) {
        return CropScheduleResponse.of(cropScheduleService.getById(cropScheduleId));
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
