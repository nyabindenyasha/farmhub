package com.xplug.tech.crop;

import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleUpdateRequest;
import com.xplug.tech.enums.CropScheduleType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-fertilizer-schedule")
public class CropFertilizerScheduleController {

    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    public CropFertilizerScheduleController(CropFertilizerScheduleService cropFertilizerScheduleService) {
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
    }

    @GetMapping
    @Operation(summary = "Get All CropFertilizerSchedules")
    public List<CropFertilizerSchedule> getAll() {
        return cropFertilizerScheduleService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CropFertilizerSchedule Info By Id")
    public CropFertilizerSchedule getById(@PathVariable Long id) {
        return cropFertilizerScheduleService.getById(id);
    }

    @GetMapping("/crop/{cropId}")
    @Operation(summary = "Get CropFertilizerSchedule Info By Crop Id")
    public List<CropFertilizerSchedule> getById(@RequestParam CropScheduleType cropScheduleType, @PathVariable Long cropId) {
        return cropFertilizerScheduleService.getByCropAndScheduleType(cropId, cropScheduleType);
    }


    @PostMapping
    @Operation(summary = "Create CropFertilizerSchedule")
    public CropFertilizerSchedule create(@RequestBody CropFertilizerScheduleRequest cropFertilizerScheduleRequest) {
        return cropFertilizerScheduleService.create(cropFertilizerScheduleRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropFertilizerSchedule")
    public CropFertilizerSchedule update(@RequestBody CropFertilizerScheduleUpdateRequest cropFertilizerScheduleUpdateRequest) {
        return cropFertilizerScheduleService.update(cropFertilizerScheduleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropFertilizerSchedule")
    public void delete(@PathVariable Long id) {
        cropFertilizerScheduleService.delete(id);
    }

}
