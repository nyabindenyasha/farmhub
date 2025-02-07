package com.xplug.tech.crop;

import com.xplug.tech.cropbatch.CropBatchRequest;
import com.xplug.tech.cropbatch.CropBatchResponse;
import com.xplug.tech.cropbatch.CropBatchService;
import com.xplug.tech.cropbatch.CropBatchUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CropBatchResponse> getAll() {
        return cropBatchService.getAll().stream().map(CropBatchResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Crop Batch Info By Id")
    public CropBatchResponse getById(@PathVariable Long id) {
        return CropBatchResponse.of(cropBatchService.getById(id));
    }

    @GetMapping("/user/{userAccountId}")
    @Operation(summary = "Get Crop Batch Info By Farmer Id")
    public List<CropBatchResponse> getByFarmer(@PathVariable Long userAccountId) {
        return cropBatchService.getByFarmer(userAccountId).stream().map(CropBatchResponse::of).collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Create Crop Batch")
    public CropBatchResponse create(@RequestBody CropBatchRequest cropBatchRequest) {
        return CropBatchResponse.of(cropBatchService.create(cropBatchRequest));
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
