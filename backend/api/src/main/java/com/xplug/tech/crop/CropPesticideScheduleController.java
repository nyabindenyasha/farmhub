package com.xplug.tech.crop;

import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleUpdateRequest;
import com.xplug.tech.enums.CropScheduleType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-pesticide-schedule")
public class CropPesticideScheduleController {

    private final CropPesticideScheduleService cropPesticideScheduleService;

    public CropPesticideScheduleController(CropPesticideScheduleService cropPesticideScheduleService) {
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }

    @GetMapping
    @Operation(summary = "Get All cropPesticideSchedules")
    public List<CropPesticideSchedule> getAll() {
        return cropPesticideScheduleService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cropPesticideSchedule Info By Id")
    public CropPesticideSchedule getById(@PathVariable Long id) {
        return cropPesticideScheduleService.getById(id);
    }

    @GetMapping("/crop/{cropId}")
    @Operation(summary = "Get CropPesticideSchedule Info By Crop Id")
    public List<CropPesticideSchedule> getById(@RequestParam CropScheduleType cropScheduleType, @PathVariable Long cropId) {
        return cropPesticideScheduleService.getByCropAndScheduleType(cropId, cropScheduleType);
    }

    @PostMapping
    @Operation(summary = "Create cropPesticideSchedule")
    public CropPesticideSchedule create(@RequestBody CropPesticideScheduleRequest cropPesticideScheduleRequest) {
        return cropPesticideScheduleService.create(cropPesticideScheduleRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update cropPesticideSchedule")
    public CropPesticideSchedule update(@RequestBody CropPesticideScheduleUpdateRequest cropPesticideScheduleUpdateRequest) {
        return cropPesticideScheduleService.update(cropPesticideScheduleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete cropPesticideSchedule")
    public void delete(@PathVariable Long id) {
        cropPesticideScheduleService.delete(id);
    }

}
