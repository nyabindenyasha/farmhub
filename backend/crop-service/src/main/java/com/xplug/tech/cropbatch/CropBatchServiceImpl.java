package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.*;
import com.xplug.tech.cropprogram.CropProgramService;
import com.xplug.tech.event.CropBatchCreatedEvent;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.exception.ItemNotFoundException;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.user.UserAccountService;
import com.xplug.tech.utils.PeriodUtils;
import com.xplug.tech.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public non-sealed class CropBatchServiceImpl implements CropBatchService {

    private final CropBatchDao cropBatchRepository;

    private final CropProgramService cropProgramService;

    private final CropScheduleTaskDao cropScheduleTaskDao;

    private final UserAccountService userAccountService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CropBatchServiceImpl(CropBatchDao cropBatchRepository, CropProgramService cropProgramService, CropScheduleTaskDao cropScheduleTaskDao, UserAccountService userAccountService, ApplicationEventPublisher applicationEventPublisher) {
        this.cropBatchRepository = cropBatchRepository;
        this.cropProgramService = cropProgramService;
        this.cropScheduleTaskDao = cropScheduleTaskDao;
        this.userAccountService = userAccountService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public List<CropBatch> getAll() {
        return cropBatchRepository.findAll();
    }

    @Override
    public List<CropBatch> getByFarmer(Long userAccountId) {
        return cropBatchRepository.findByUserAccountId(userAccountId);
    }

    public CropBatch getById(Long id) {
        var cropBatch = cropBatchRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Id: " + id));
        cropBatch.setFertilizerScheduleTasks(cropScheduleTaskDao.findByCropBatchId(id).stream().filter(CropFertilizerScheduleTask.class::isInstance).map(CropFertilizerScheduleTask.class::cast).collect(Collectors.toSet()));
        cropBatch.setPesticideScheduleTasks(cropScheduleTaskDao.findByCropBatchId(id).stream().filter(CropPesticideScheduleTask.class::isInstance).map(CropPesticideScheduleTask.class::cast).collect(Collectors.toSet()));
        return cropBatch;
    }

    public CropBatch create(CropBatchRequest cropBatchRequest) {


        var optionalCropFarmer = cropBatchRepository
                .findByCropProgramIdAndUserAccountIdAndAndDateOfTransplant(cropBatchRequest.getCropProgramId(), cropBatchRequest.getFarmerId(), cropBatchRequest.getDateOfTransplant());
        if (optionalCropFarmer.isPresent()) {
            throw new ItemAlreadyExistsException("CropBatch with same farmer and crop already exists");
        }

        UserAccount userAccount = userAccountService.findById(cropBatchRequest.getFarmerId());

        validateFarmer(userAccount);

        CropProgram cropProgram = cropProgramService.getById(cropBatchRequest.getCropProgramId());

        // Step 2: Create CropBatch
        CropBatch cropBatch = CropBatch.builder()
                .userAccount(userAccount)
                .cropProgram(cropProgram)
                .dateOfTransplant(cropBatchRequest.getDateOfTransplant())
                .location(cropBatchRequest.getLocation())
                .remarks(cropBatchRequest.getRemarks())
                .build();

        // Step 4: Save CropBatch (Cascade will save Tasks)
        CropBatch savedCropBatch = cropBatchRepository.save(cropBatch);

        // Step 3: Create Tasks from CropPesticideSchedule
        Set<CropPesticideSchedule> pesticideSchedules = cropProgram.getPesticideScheduleList();

        //todo bidirectional mapping
        Set<CropPesticideScheduleTask> savedPesticideSchedules = new HashSet<>();

        for (CropPesticideSchedule pesticideSchedule : pesticideSchedules) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(savedCropBatch.getDateOfTransplant(), pesticideSchedule.getStageOfGrowth());
            CropPesticideScheduleTask pesticideScheduleTask = CropPesticideScheduleTask.builder()
                    .cropPesticideSchedule(pesticideSchedule)
                    .cropBatch(savedCropBatch)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            savedPesticideSchedules.add(cropScheduleTaskDao.save(pesticideScheduleTask));
        }

        Set<CropFertilizerSchedule> fertilizerSchedules = cropProgram.getFertilizerScheduleList();

        //todo bidirectional mapping
        Set<CropFertilizerScheduleTask> savedFertilizerSchedules = new HashSet<>();

        for (CropFertilizerSchedule fertilizerSchedule : fertilizerSchedules) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(savedCropBatch.getDateOfTransplant(), fertilizerSchedule.getStageOfGrowth());
            CropFertilizerScheduleTask fertilizerScheduleTask = CropFertilizerScheduleTask.builder()
                    .cropFertilizerSchedule(fertilizerSchedule)
                    .cropBatch(savedCropBatch)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            savedFertilizerSchedules.add(cropScheduleTaskDao.save(fertilizerScheduleTask));
        }
        applicationEventPublisher.publishEvent(new CropBatchCreatedEvent(this, savedCropBatch));

        //todo
        savedCropBatch.setFertilizerScheduleTasks(savedFertilizerSchedules);
        savedCropBatch.setPesticideScheduleTasks(savedPesticideSchedules);
        return savedCropBatch;
    }

    public CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest) {
        var cropBatch = getById(cropBatchUpdateRequest.getId());
        return cropBatchRepository.save(cropBatch);
    }

    public void delete(Long id) {
        CropBatch cropBatch = getById(id);
        cropBatchRepository.delete(cropBatch);
    }


    private void validateFarmer(UserAccount userAccount) {
        if (!userAccount.getGroup().getName().equals(UserGroupEnum.FARMER.name())) {
            throw new InvalidRequestException("User Account must have role: FARMER");
        }
    }

}
