package com.xplug.tech.fertilizer;

import com.xplug.tech.crop.Fertilizer;
import com.xplug.tech.crop.FertilizerDao;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public non-sealed class FertilizerServiceImpl implements FertilizerService {

    private final FertilizerDao fertilizerRepository;

    private final FertilizerMapper fertilizerMapper;

    public FertilizerServiceImpl(FertilizerDao fertilizerRepository, FertilizerMapper fertilizerMapper) {
        this.fertilizerRepository = fertilizerRepository;
        this.fertilizerMapper = fertilizerMapper;
    }

    public List<Fertilizer> getAll() {
        return fertilizerRepository.findAll();
    }

    public Fertilizer getById(Long id) {
        return fertilizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fertilizer not found with ID: " + id));
    }

    @Override
    public Fertilizer getByName(String name) {
        return fertilizerRepository.findByNameIgnoreCaseOrAliasIgnoreCase(name, name)
                .orElseThrow(() -> new RuntimeException("Fertilizer not found with Name: " + name));
    }

    public Fertilizer create(FertilizerRequest fertilizerRequest) {
        var optionalFertilizer = fertilizerRepository.findByNameIgnoreCaseOrAliasIgnoreCase(fertilizerRequest.getName(), fertilizerRequest.getAlias());
        if (optionalFertilizer.isPresent()) {
            throw new ItemAlreadyExistsException("Fertilizer with name: " + fertilizerRequest.getName() + " already exists");
        }
        return saveFertilizer(fertilizerRequest);
    }

    private Fertilizer saveFertilizer(FertilizerRequest fertilizerRequest) {
        var fertilizer = fertilizerMapper.fertilizerFromFertilizerRequest(fertilizerRequest);
        return fertilizerRepository.save(fertilizer);
    }


    @Override
    public Fertilizer initialize(FertilizerRequest fertilizerRequest) {
        var optionalFertilizer = fertilizerRepository.findByNameIgnoreCaseOrAliasIgnoreCase(fertilizerRequest.getName(), fertilizerRequest.getAlias());
        if (optionalFertilizer.isPresent()) {
            log.info("### Fertilizer Found {}", fertilizerRequest.getName());
            return optionalFertilizer.get();
        }
        return saveFertilizer(fertilizerRequest);
    }

    @Override
    public List<Fertilizer> createBulk(List<FertilizerRequest> fertilizerRequests) {
//        var fertilizers = fertilizerRequests.stream().map(fertilizerMapper::fertilizerFromFertilizerRequest).collect(Collectors.toList());
//        return fertilizerRepository.saveAll(fertilizers);
        var fertilizers = new ArrayList<Fertilizer>();
        fertilizerRequests.stream().forEach(fertilizerRequest -> {
            var savedFertilizer = initialize(fertilizerRequest);
            fertilizers.add(savedFertilizer);
        });
        return fertilizers;
    }

    public Fertilizer update(FertilizerUpdateRequest fertilizerUpdateRequest) {
        var fertilizer = getById(fertilizerUpdateRequest.getId());
        var updatedFertilizer = fertilizerMapper.fertilizerFromFertilizerUpdateRequest(fertilizer, fertilizerUpdateRequest);
        return fertilizerRepository.save(updatedFertilizer);
    }

    public void delete(Long id) {
        Fertilizer fertilizer = getById(id);
        fertilizerRepository.delete(fertilizer);
    }

}
