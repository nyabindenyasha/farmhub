package com.xplug.tech.crop;

import com.xplug.tech.cropprogram.*;
import com.xplug.tech.cropprograms.CropProgramPDFGenerator;
import com.xplug.tech.cropprograms.CropProgramPDFGeneratorTest;
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

    @GetMapping("/crop/{cropId}")
    @Operation(summary = "Get Crop Programs By CropId")
    public List<CropProgramResponse> getByCrop(@PathVariable Long cropId) {
        return cropProgramService.getByCrop(cropId).stream().map(CropProgramResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Crop Program Info By Id")
    public CropProgramResponse getById(@PathVariable Long id) {
        return CropProgramResponse.of(cropProgramService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create CropSchedule")
    public CropProgram create(@RequestBody CropProgramRequest cropProgramRequest) {
        return cropProgramService.create(cropProgramRequest);
    }

    @PostMapping("/v2")
    @Operation(summary = "Create V2 CropSchedule")
    public CropProgram create(@RequestBody CropProgramRequestV2 cropProgramRequest) {
        return cropProgramService.create(cropProgramRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropSchedule")
    public CropProgram update(@RequestBody CropProgramUpdateRequest cropScheduleUpdateRequest) {
        return cropProgramService.update(cropScheduleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropSchedule")
    public void delete(@PathVariable Long id) {
        cropProgramService.delete(id);
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
