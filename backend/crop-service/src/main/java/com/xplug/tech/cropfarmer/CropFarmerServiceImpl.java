package com.xplug.tech.cropfarmer;

import com.xplug.tech.crop.CropFarmer;
import com.xplug.tech.crop.CropFarmerDao;
import com.xplug.tech.event.CropFarmerCreatedEvent;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.user.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropFarmerServiceImpl implements CropFarmerService {

    private final CropFarmerDao cropFarmerRepository;

    private final CropFarmerMapper cropFarmerMapper;

    private final UserAccountService userAccountService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CropFarmerServiceImpl(CropFarmerDao cropFarmerRepository, CropFarmerMapper cropFarmerMapper, UserAccountService userAccountService, ApplicationEventPublisher applicationEventPublisher) {
        this.cropFarmerRepository = cropFarmerRepository;
        this.cropFarmerMapper = cropFarmerMapper;
        this.userAccountService = userAccountService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public List<CropFarmer> getAll() {
        return cropFarmerRepository.findAll();
    }

    public CropFarmer getById(Long id) {
        return cropFarmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropFarmer not found with ID: " + id));
    }

    public CropFarmer create(CropFarmerRequest cropFarmerRequest) {
        //can also validate by cropProgramId i.e. cropSchedule
        var optionalCropFarmer = cropFarmerRepository
                .findByCropIdAndUserAccountIdAndAndDateOfTransplant(cropFarmerRequest.getCropId(), cropFarmerRequest.getFarmerId(), cropFarmerRequest.getDateOfTransplant());
        if (optionalCropFarmer.isPresent()) {
            throw new ItemAlreadyExistsException("CropFarmer with same farmer and crop already exists");
        }
        validateFarmer(cropFarmerRequest.getFarmerId());
        var cropFarmer = cropFarmerMapper.cropFarmerFromCropFarmerRequest(cropFarmerRequest);
        var savedCropFarmer = cropFarmerRepository.save(cropFarmer);
        applicationEventPublisher.publishEvent(new CropFarmerCreatedEvent(this, savedCropFarmer, cropFarmerRequest.getCropProgramId()));
        return savedCropFarmer;
    }

    public CropFarmer update(CropFarmerUpdateRequest cropFarmerUpdateRequest) {
        var cropFarmer = getById(cropFarmerUpdateRequest.getId());
        var updatedCropFarmer = cropFarmerMapper.cropFarmerFromCropFarmerUpdateRequest(cropFarmer, cropFarmerUpdateRequest);
        return cropFarmerRepository.save(updatedCropFarmer);
    }

    public void delete(Long id) {
        CropFarmer crop = getById(id);
        cropFarmerRepository.delete(crop);
    }

    private void validateFarmer(Long userAccountId) {
        UserAccount userAccount = userAccountService.findById(userAccountId);
        if (!userAccount.getGroup().getName().equals(UserGroupEnum.FARMER.name())) {
            throw new InvalidRequestException("User Account must have role: FARMER");
        }
    }

}
