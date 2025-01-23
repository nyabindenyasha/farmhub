package com.xplug.tech.crop;

import com.xplug.tech.pesticide.PesticideRequest;
import com.xplug.tech.pesticide.PesticideService;
import com.xplug.tech.pesticide.PesticideUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/api/pesticide")
public class PesticideController {

    private final PesticideService pesticideService;

    public PesticideController(PesticideService pesticideService) {
        this.pesticideService = pesticideService;
    }

    @GetMapping
    @Operation(summary = "Get All Pesticides")
    public List<Pesticide> getAll() {
        return pesticideService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Pesticide Info By Id")
    public Pesticide getById(@PathVariable Long id) {
        return pesticideService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create Pesticide")
    public Pesticide create(@RequestBody PesticideRequest pesticideRequest) {
        return pesticideService.create(pesticideRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Pesticide")
    public Pesticide update(@RequestBody PesticideUpdateRequest pesticideUpdateRequest) {
        return pesticideService.update(pesticideUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Pesticide")
    public void delete(@PathVariable Long id) {
        pesticideService.delete(id);
    }

}
