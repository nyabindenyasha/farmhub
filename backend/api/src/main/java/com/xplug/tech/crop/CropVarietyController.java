package com.xplug.tech.crop;

import com.xplug.tech.cropvariety.CropVarietyRequest;
import com.xplug.tech.cropvariety.CropVarietyService;
import com.xplug.tech.cropvariety.CropVarietyUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-variety")
public class CropVarietyController {

    private final CropVarietyService cropVarietyService;

    public CropVarietyController(CropVarietyService cropVarietyService) {
        this.cropVarietyService = cropVarietyService;
    }

    @GetMapping
    @Operation(summary = "Get All Crop Varieties")
    public List<CropVariety> getAll() {
        return cropVarietyService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CropVariety Info By Id")
    public CropVariety getById(@PathVariable Long id) {
        return cropVarietyService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create CropVariety")
    public CropVariety create(@RequestBody CropVarietyRequest cropVarietyRequest) {
        return cropVarietyService.create(cropVarietyRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropVariety")
    public CropVariety update(@RequestBody CropVarietyUpdateRequest cropVarietyUpdateRequest) {
        return cropVarietyService.update(cropVarietyUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropVariety")
    public void delete(@PathVariable Long id) {
        cropVarietyService.delete(id);
    }

}
