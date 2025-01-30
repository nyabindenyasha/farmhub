package com.xplug.tech.crop;

import com.xplug.tech.cropbatch.CropBatchRequest;
import com.xplug.tech.cropbatch.CropBatchService;
import com.xplug.tech.cropbatch.CropBatchUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/crop/batch")
public class CropBatchController {

    private final CropBatchService cropBatchService;

    public CropBatchController(CropBatchService cropBatchService) {
        this.cropBatchService = cropBatchService;
    }

    @GetMapping
    @Operation(summary = "Get All Crop Batches")
    public List<CropBatch> getAll() {
        return cropBatchService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Crop Batch Info By Id")
    public CropBatch getById(@PathVariable Long id) {
        return cropBatchService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create Crop Batch")
    public CropBatch create(@RequestBody CropBatchRequest cropBatchRequest) {
        return cropBatchService.create(cropBatchRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Crop Batch")
    public CropBatch update(@RequestBody CropBatchUpdateRequest cropBatchUpdateRequest) {
        return cropBatchService.update(cropBatchUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Crop Batch")
    public void delete(@PathVariable Long id) {
        cropBatchService.delete(id);
    }

}
