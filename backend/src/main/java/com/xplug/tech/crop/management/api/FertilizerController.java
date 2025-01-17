package com.xplug.tech.crop.management.api;

import com.xplug.tech.crop.management.domain.Fertilizer;
import com.xplug.tech.crop.management.service.fertilizer.FertilizerRequest;
import com.xplug.tech.crop.management.service.fertilizer.FertilizerService;
import com.xplug.tech.crop.management.service.fertilizer.FertilizerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/fertilizer")
public class FertilizerController {

    private final FertilizerService fertilizerService;

    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @GetMapping
    @Operation(summary = "Get All Fertilizers")
    public List<Fertilizer> getAll() {
        return fertilizerService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Fertilizer Info By Id")
    public Fertilizer getById(@PathVariable Long id) {
        return fertilizerService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create Fertilizer")
    public Fertilizer create(@RequestBody FertilizerRequest fertilizerRequest) {
        return fertilizerService.create(fertilizerRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Fertilizer")
    public Fertilizer update(@RequestBody FertilizerUpdateRequest fertilizerUpdateRequest) {
        return fertilizerService.update(fertilizerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Fertilizer")
    public void delete(@PathVariable Long id) {
        fertilizerService.delete(id);
    }

}
