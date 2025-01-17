package com.xplug.tech.crop.management.api;

import com.xplug.tech.crop.management.domain.CropSchedule;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleRequest;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleService;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop-schedule")
public class CropScheduleController {

    private final CropScheduleService cropScheduleService;

    public CropScheduleController(CropScheduleService cropScheduleService) {
        this.cropScheduleService = cropScheduleService;
    }

    @GetMapping
    @Operation(summary = "Get All CropSchedules")
    public List<CropSchedule> getAll() {
        return cropScheduleService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CropSchedule Info By Id")
    public CropSchedule getById(@PathVariable Long id) {
        return cropScheduleService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create CropSchedule")
    public CropSchedule create(@RequestBody CropScheduleRequest cropScheduleRequest) {
        return cropScheduleService.create(cropScheduleRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CropSchedule")
    public CropSchedule update(@RequestBody CropScheduleUpdateRequest cropScheduleUpdateRequest) {
        return cropScheduleService.update(cropScheduleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CropSchedule")
    public void delete(@PathVariable Long id) {
        cropScheduleService.delete(id);
    }

}
