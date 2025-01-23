package com.xplug.tech.crop;

import com.xplug.tech.cropfarmer.CropFarmerRequest;
import com.xplug.tech.cropfarmer.CropFarmerService;
import com.xplug.tech.cropfarmer.CropFarmerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-farmer")
public class CropFarmerController {

    private final CropFarmerService cropFarmerService;

    public CropFarmerController(CropFarmerService cropFarmerService) {
        this.cropFarmerService = cropFarmerService;
    }

    @GetMapping
    @Operation(summary = "Get All CropFarmers")
    public List<CropFarmer> getAll() {
        return cropFarmerService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CropFarmer Info By Id")
    public CropFarmer getById(@PathVariable Long id) {
        return cropFarmerService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create CropFarmer")
    public CropFarmer create(@RequestBody CropFarmerRequest cropFarmerRequest) {
        return cropFarmerService.create(cropFarmerRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropFarmer")
    public CropFarmer update(@RequestBody CropFarmerUpdateRequest cropFarmerUpdateRequest) {
        return cropFarmerService.update(cropFarmerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropFarmer")
    public void delete(@PathVariable Long id) {
        cropFarmerService.delete(id);
    }

}
