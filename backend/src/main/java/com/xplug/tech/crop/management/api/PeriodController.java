package com.xplug.tech.crop.management.api;

import com.xplug.tech.crop.management.domain.Period;
import com.xplug.tech.crop.management.service.period.PeriodRequest;
import com.xplug.tech.crop.management.service.period.PeriodService;
import com.xplug.tech.crop.management.service.period.PeriodUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/period")
public class PeriodController {

    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping
    @Operation(summary = "Get All Periods")
    public List<Period> getAll() {
        return periodService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Period Info By Id")
    public Period getById(@PathVariable Long id) {
        return periodService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create Period")
    public Period create(@RequestBody PeriodRequest periodRequest) {
        return periodService.create(periodRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Period")
    public Period update(@RequestBody PeriodUpdateRequest periodUpdateRequest) {
        return periodService.update(periodUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Period")
    public void delete(@PathVariable Long id) {
        periodService.delete(id);
    }

}
