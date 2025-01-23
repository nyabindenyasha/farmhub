package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.crop.CropStagesOfGrowth;
import com.xplug.tech.crop.CropStagesOfGrowthDao;
import com.xplug.tech.period.PeriodRequest;
import com.xplug.tech.period.PeriodService;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public non-sealed class CropStagesOfGrowthServiceImpl implements CropStagesOfGrowthService {

    private final CropStagesOfGrowthDao cropStagesOfGrowthRepository;

    private final CropStagesOfGrowthMapper cropStagesOfGrowthMapper;

    private final PeriodService periodService;

    public CropStagesOfGrowthServiceImpl(CropStagesOfGrowthDao cropStagesOfGrowthRepository, CropStagesOfGrowthMapper cropStagesOfGrowthMapper, PeriodService periodService) {
        this.cropStagesOfGrowthRepository = cropStagesOfGrowthRepository;
        this.cropStagesOfGrowthMapper = cropStagesOfGrowthMapper;
        this.periodService = periodService;
    }


    public List<CropStagesOfGrowth> getAll() {
        return cropStagesOfGrowthRepository.findAll();
    }

    public CropStagesOfGrowth getById(Long id) {
        return cropStagesOfGrowthRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropStagesOfGrowth not found with ID: " + id));
    }

    @Override
    public List<CropStagesOfGrowth> getByCropId(Long cropId) {
        var cropStagesOfGrowthList = cropStagesOfGrowthRepository.findByCropId(cropId);
        return cropStagesOfGrowthList;
    }

    public CropStagesOfGrowth create(CropStagesOfGrowthRequest request) {
        var stageStartDatePeriod = periodService.findOrCreatePeriod(request.getStageStartDate());
        var stageEndDatePeriod = periodService.findOrCreatePeriod(request.getStageEndDate());
        var optionalCropStagesOfGrowth = cropStagesOfGrowthRepository
                .findByCropIdAndStageStartDateIdAndStageEndDateId(request.getCropId(), stageStartDatePeriod.getId(), stageEndDatePeriod.getId());
        if (optionalCropStagesOfGrowth.isPresent()) {
            throw new ItemAlreadyExistsException("CropStagesOfGrowth with same period already exists");
        }
        var cropStagesOfGrowth = cropStagesOfGrowthMapper.cropStagesOfGrowthFromCropStagesOfGrowthRequest(request);
        return cropStagesOfGrowthRepository.save(cropStagesOfGrowth);
    }

    @Override
    public void initialize(CropStagesOfGrowthRequest request) {

        var cropStagesOfGrowthRequests = buildRequest(request);

        log.info("cropStagesOfGrowthRequests {}", cropStagesOfGrowthRequests);

        cropStagesOfGrowthRequests.forEach(cropStagesOfGrowthRequest -> {
            var stageStartDatePeriod = periodService.findOrCreatePeriod(cropStagesOfGrowthRequest.getStageStartDate());
            var stageEndDatePeriod = periodService.findOrCreatePeriod(cropStagesOfGrowthRequest.getStageEndDate());
            var optionalCrop = cropStagesOfGrowthRepository
                    .findByCropIdAndStageStartDateIdAndStageEndDateId(cropStagesOfGrowthRequest.getCropId(), stageStartDatePeriod.getId(), stageEndDatePeriod.getId());
            if (optionalCrop.isPresent()) {
                log.info("### CropStagesOfGrowth Found {}", optionalCrop.get());
                return;
            }
            var cropStagesOfGrowth = cropStagesOfGrowthMapper.cropStagesOfGrowthFromCropStagesOfGrowthRequest(cropStagesOfGrowthRequest);
            var savedCropStagesOfGrowth = cropStagesOfGrowthRepository.save(cropStagesOfGrowth);
            log.info("Saved CropStagesOfGrowth {}", savedCropStagesOfGrowth);
        });

    }

    List<CropStagesOfGrowthRequest> buildRequest(CropStagesOfGrowthRequest request) {
        List<CropStagesOfGrowthRequest> cropStagesOfGrowthRequests = new ArrayList<>();
        if (request.getStageStartDate().getPeriodUnit().equals(request.getStageEndDate().getPeriodUnit())) {
            for (int i = request.getStageStartDate().getPeriodValue(); i <= request.getStageEndDate().getPeriodValue(); i++) {

                var stageStartDatePeriodRequest = new PeriodRequest(request.getStageStartDate().getPeriodUnit(), i);

                CropStagesOfGrowthRequest newRequest = new CropStagesOfGrowthRequest();
                newRequest.setCropId(request.getCropId());
                newRequest.setStageOfGrowth(request.getStageOfGrowth());
                newRequest.setStageStartDate(stageStartDatePeriodRequest);
                newRequest.setStageEndDate(request.getStageEndDate());
                cropStagesOfGrowthRequests.add(newRequest);
            }
        }
        return cropStagesOfGrowthRequests;
    }

    public CropStagesOfGrowth update(CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest) {
        var cropStagesOfGrowth = getById(cropStagesOfGrowthUpdateRequest.getId());
        var updatedCropStagesOfGrowth = cropStagesOfGrowthMapper
                .cropStagesOfGrowthFromCropStagesOfGrowthUpdateRequest(cropStagesOfGrowth, cropStagesOfGrowthUpdateRequest);
        return cropStagesOfGrowthRepository.save(updatedCropStagesOfGrowth);
    }

    public void delete(Long id) {
        CropStagesOfGrowth cropStagesOfGrowth = getById(id);
        cropStagesOfGrowthRepository.delete(cropStagesOfGrowth);
    }

}
