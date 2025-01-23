package com.xplug.tech.crop;

import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthRequest;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthService;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-stages-of-growth")
public class CropStagesOfGrowthController {

    private final CropStagesOfGrowthService cropStagesOfGrowthService;

    public CropStagesOfGrowthController(CropStagesOfGrowthService cropStagesOfGrowthService) {
        this.cropStagesOfGrowthService = cropStagesOfGrowthService;
    }

    @GetMapping
    @Operation(summary = "Get All CropStagesOfGrowths")
    public List<CropStagesOfGrowth> getAll() {
        return cropStagesOfGrowthService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CropStagesOfGrowth Info By Id")
    public CropStagesOfGrowth getById(@PathVariable Long id) {
        return cropStagesOfGrowthService.getById(id);
    }

    @GetMapping("/crop/{cropId}")
    @Operation(summary = "Get CropStagesOfGrowth Info By Crop Id")
    public List<CropStagesOfGrowth> getByCropId(@PathVariable Long cropId) {
        return cropStagesOfGrowthService.getByCropId(cropId);
    }

    @PostMapping
    @Operation(summary = "Create CropStagesOfGrowth")
    public CropStagesOfGrowth create(@RequestBody CropStagesOfGrowthRequest cropStagesOfGrowthRequest) {
        return cropStagesOfGrowthService.create(cropStagesOfGrowthRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropStagesOfGrowth")
    public CropStagesOfGrowth update(@RequestBody CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest) {
        return cropStagesOfGrowthService.update(cropStagesOfGrowthUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropStagesOfGrowth")
    public void delete(@PathVariable Long id) {
        cropStagesOfGrowthService.delete(id);
    }

}
