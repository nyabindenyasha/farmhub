package com.xplug.tech.crop;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping
    @Operation(summary = "Get All Crops")
    public List<Crop> getAll() {
        return cropService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Crop Info By Id")
    public Crop getById(@PathVariable Long id) {
        return cropService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create Crop")
    public Crop create(@RequestBody CropRequest cropRequest) {
        return cropService.create(cropRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Crop")
    public Crop update(@RequestBody CropUpdateRequest cropUpdateRequest) {
        return cropService.update(cropUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Crop")
    public void delete(@PathVariable Long id) {
        cropService.delete(id);
    }

}
