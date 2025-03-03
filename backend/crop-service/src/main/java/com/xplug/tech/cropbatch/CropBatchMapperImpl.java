package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.*;
import com.xplug.tech.cropprogram.CropProgramService;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.user.UserAccountService;
import com.xplug.tech.utils.PeriodUtils;
import com.xplug.tech.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
public non-sealed class CropBatchMapperImpl implements CropBatchMapper {

    private final CropProgramService cropProgramService;

    private final UserAccountService userAccountService;

    public CropBatchMapperImpl(CropProgramService cropProgramService, UserAccountService userAccountService) {
        this.cropProgramService = cropProgramService;
        this.userAccountService = userAccountService;
    }

    @Override
    public CropBatch cropBatchFromCropBatchRequest(CropBatchRequest cropBatchRequest) {
        Objects.requireNonNull(cropBatchRequest, "CropBatchRequest cannot be null!");

        UserAccount userAccount = userAccountService.findById(cropBatchRequest.getFarmerId());

        validateFarmer(userAccount);

        CropProgram cropProgram = cropProgramService.getById(cropBatchRequest.getCropProgramId());

        Set<CropFertilizerScheduleTask> fertilizerScheduleTasks = new HashSet<>();
        Set<CropPesticideScheduleTask> pesticideScheduleTasks = new HashSet<>();

        for (CropFertilizerSchedule fertilizerSchedule : cropProgram.getFertilizerScheduleList()) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(cropBatchRequest.getDateOfTransplant(), fertilizerSchedule.getStageOfGrowth());
            CropFertilizerScheduleTask fertilizerScheduleTask = CropFertilizerScheduleTask.builder()
                    .cropFertilizerSchedule(fertilizerSchedule)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            fertilizerScheduleTasks.add(fertilizerScheduleTask);
        }

        for (CropPesticideSchedule pesticideSchedule : cropProgram.getPesticideScheduleList()) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(cropBatchRequest.getDateOfTransplant(), pesticideSchedule.getStageOfGrowth());
            CropPesticideScheduleTask pesticideScheduleTask = CropPesticideScheduleTask.builder()
                    .cropPesticideSchedule(pesticideSchedule)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            pesticideScheduleTasks.add(pesticideScheduleTask);
        }


        return CropBatch.builder()
                .userAccount(userAccount)
                .cropProgram(cropProgram)
                .dateOfTransplant(cropBatchRequest.getDateOfTransplant())
                .location(cropBatchRequest.getLocation())
                .remarks(cropBatchRequest.getRemarks())
                .fertilizerScheduleTasks(fertilizerScheduleTasks)
                .pesticideScheduleTasks(pesticideScheduleTasks)
                .build();
    }

    @Override
    public CropBatch cropBatchFromCropBatchUpdateRequest(CropBatch cropBatch, CropBatchUpdateRequest cropBatchUpdateRequest) {
        Objects.requireNonNull(cropBatch, "CropBatch cannot be null!");
        Objects.requireNonNull(cropBatchUpdateRequest, "CropBatchUpdateRequest cannot be null!");
        return cropBatch;
    }

    @Override
    public CropBatchResponse cropBatchResponseFromCropBatch(CropBatch cropBatch) {
        return CropBatchResponse.of(cropBatch);
    }

    private void validateFarmer(UserAccount userAccount) {
        if (!userAccount.getGroup().getName().equals(UserGroupEnum.FARMER.name())) {
            throw new InvalidRequestException("User Account must have role: FARMER");
        }
    }

}
