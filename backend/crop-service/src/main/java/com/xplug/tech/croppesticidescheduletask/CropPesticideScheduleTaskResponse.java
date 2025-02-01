package com.xplug.tech.croppesticidescheduletask;

import com.xplug.tech.crop.CropPesticideScheduleTask;
import com.xplug.tech.enums.PesticideApplicationMethod;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.period.PeriodResponse;
import com.xplug.tech.pesticide.PesticideResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class CropPesticideScheduleTaskResponse {

    private Long id;

    private PesticideResponse pesticide;

    private PeriodResponse stageOfGrowth;

    private PeriodResponse applicationInterval;  //period data-type

    private PesticideApplicationMethod applicationMethod; //enum

    private String remarks;

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    private TaskStatus taskStatus;

    private LocalDateTime taskDate;

    public static CropPesticideScheduleTaskResponse of(CropPesticideScheduleTask cropPesticideScheduleTask) {
        Objects.requireNonNull(cropPesticideScheduleTask, "CropPesticideScheduleTask cannot be null!");
        return CropPesticideScheduleTaskResponse.builder()
                .id(cropPesticideScheduleTask.getId())
                .pesticide(PesticideResponse.of(cropPesticideScheduleTask.getCropPesticideSchedule().getPesticide()))
                .stageOfGrowth(PeriodResponse.of(cropPesticideScheduleTask.getCropPesticideSchedule().getStageOfGrowth()))
                .applicationInterval(PeriodResponse.of(cropPesticideScheduleTask.getCropPesticideSchedule().getApplicationInterval()))
                .applicationMethod(cropPesticideScheduleTask.getCropPesticideSchedule().getApplicationMethod())
                .remarks(cropPesticideScheduleTask.getCropPesticideSchedule().getRemarks())
                .isCompleted(cropPesticideScheduleTask.getIsCompleted())
                .completionDate(cropPesticideScheduleTask.getCompletionDate())
                .taskRemarks(cropPesticideScheduleTask.getTaskRemarks())
                .taskDate(cropPesticideScheduleTask.getTaskDate())
                .taskStatus(cropPesticideScheduleTask.getTaskStatus())
                .build();
    }

}
