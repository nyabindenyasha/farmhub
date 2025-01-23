package com.xplug.tech.period;

import com.xplug.tech.crop.Period;
import com.xplug.tech.crop.PeriodDao;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class PeriodServiceImpl implements PeriodService {

    private final PeriodDao periodRepository;

    private final PeriodMapper periodMapper;

    public PeriodServiceImpl(PeriodDao periodRepository, PeriodMapper periodMapper) {
        this.periodRepository = periodRepository;
        this.periodMapper = periodMapper;
    }


    public List<Period> getAll() {
        return periodRepository.findAll();
    }

    public Period getById(Long id) {
        return periodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Period not found with ID: " + id));
    }

    public Period create(PeriodRequest periodRequest) {
        var optionalPeriod = periodRepository.findByPeriodUnitAndPeriodValue(periodRequest.getPeriodUnit(), periodRequest.getPeriodValue());
        if (optionalPeriod.isPresent()) {
            throw new ItemAlreadyExistsException("Period " + periodRequest.getPeriodUnit() + " " + periodRequest.getPeriodUnit() + " already exists");
        }
        var period = periodMapper.periodFromPeriodRequest(periodRequest);
        return periodRepository.save(period);
    }

    @Override
    public Period findOrCreatePeriod(PeriodRequest periodRequest) {
        var optionalPeriod = periodRepository.findByPeriodUnitAndPeriodValue(periodRequest.getPeriodUnit(), periodRequest.getPeriodValue());

//        if (optionalPeriod.isPresent()) {
//            log.info("Period Present");
//            return optionalPeriod.get();
//        } else {
//            log.info("Period Absent");
//            return create(periodRequest);
//        }

        return optionalPeriod.orElseGet(() -> {
            log.info("Period Absent");
            return create(periodRequest);
        });
    }

    public Period update(PeriodUpdateRequest periodUpdateRequest) {
        var period = getById(periodUpdateRequest.getId());
        var updatedPeriod = periodMapper.periodFromPeriodUpdateRequest(period, periodUpdateRequest);
        return periodRepository.save(updatedPeriod);
    }

    public void delete(Long id) {
        Period period = getById(id);
        periodRepository.delete(period);
    }

}
