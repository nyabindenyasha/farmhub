package com.xplug.tech.pesticide;

import com.xplug.tech.crop.Pesticide;
import com.xplug.tech.crop.PesticideDao;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public non-sealed class PesticideServiceImpl implements PesticideService {

    private final PesticideDao pesticideRepository;

    private final PesticideMapper pesticideMapper;

    public PesticideServiceImpl(PesticideDao pesticideRepository, PesticideMapper pesticideMapper) {
        this.pesticideRepository = pesticideRepository;
        this.pesticideMapper = pesticideMapper;
    }

    public List<Pesticide> getAll() {
        return pesticideRepository.findAll();
    }

    public Pesticide getById(Long id) {
        return pesticideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pesticide not found with ID: " + id));
    }

    @Override
    public Pesticide getByName(String name) {
        return pesticideRepository.findByNameContainsIgnoreCaseOrAliasContainsIgnoreCase(name, name)
                .orElseThrow(() -> new RuntimeException("Pesticide not found with Name: " + name));
    }

    @Override
    public Pesticide create(PesticideRequest pesticideRequest) {
        var optionalCrop = pesticideRepository.findByNameContainsIgnoreCaseOrAliasContainsIgnoreCase(pesticideRequest.getName(), pesticideRequest.getAlias());
        if (optionalCrop.isPresent()) {
            throw new ItemAlreadyExistsException("Pesticide with name: " + pesticideRequest.getName() + " already exists");
        }
        return savePesticide(pesticideRequest);
    }

    private Pesticide savePesticide(PesticideRequest pesticideRequest) {
        var pesticide = pesticideMapper.pesticideFromPesticideRequest(pesticideRequest);
        return pesticideRepository.save(pesticide);
    }

    @Override
    public Pesticide initialize(PesticideRequest pesticideRequest) {
        var optionalPesticide = pesticideRepository.findByNameContainsIgnoreCaseOrAliasContainsIgnoreCase(pesticideRequest.getName(), pesticideRequest.getAlias());
        if (optionalPesticide.isPresent()) {
            log.info("### Pesticide Found {}", pesticideRequest.getName());
            return optionalPesticide.get();
        }
        return savePesticide(pesticideRequest);
    }

    @Override
    public List<Pesticide> createBulk(List<PesticideRequest> pesticideRequests) {
//        var pesticides = pesticideRequests.stream().map(pesticideMapper::pesticideFromPesticideRequest).collect(Collectors.toList());
//        return pesticideRepository.saveAll(pesticides);
        var pesticides = new ArrayList<Pesticide>();
        pesticideRequests.stream().forEach(fertilizerRequest -> {
            var savedPesticides = initialize(fertilizerRequest);
            pesticides.add(savedPesticides);
        });
        return pesticides;
    }

    public Pesticide update(PesticideUpdateRequest pesticideUpdateRequest) {
        var pesticide = getById(pesticideUpdateRequest.getId());
        var updatedPesticide = pesticideMapper.pesticideFromPesticideUpdateRequest(pesticide, pesticideUpdateRequest);
        return pesticideRepository.save(updatedPesticide);
    }

    public void delete(Long id) {
        Pesticide pesticide = getById(id);
        pesticideRepository.delete(pesticide);
    }

}
