package com.xplug.tech.crop;

import com.xplug.tech.crop.data.CropData;
import com.xplug.tech.cropguide.CropDataService;
import com.xplug.tech.cropguide.CropGuideRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop/data")
public class CropDataController {

    private final CropDataService cropDataService;

    public CropDataController(CropDataService cropDataService) {
        this.cropDataService = cropDataService;
    }

    @GetMapping
    @Operation(summary = "Get All Crop Guides")
    public List<CropData> getAll() {
        return cropDataService.getAll();
    }

    @GetMapping("/{cropId}")
    @Operation(summary = "Get By Crop Id")
    public CropData getAll(@PathVariable Long cropId) {
        return cropDataService.getByCropId(cropId);
    }

    @PostMapping
    @Operation(summary = "Create CropGuide")
    public CropData create(@RequestBody CropGuideRequest cropDataRequest) {
        return cropDataService.create(cropDataRequest);
    }

}
