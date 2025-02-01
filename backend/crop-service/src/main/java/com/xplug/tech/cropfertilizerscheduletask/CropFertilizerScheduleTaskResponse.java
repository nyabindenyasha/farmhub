package com.xplug.tech.cropfertilizerscheduletask;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropFertilizerScheduleTask;
import com.xplug.tech.enums.FertilizerApplicationMethod;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.fertilizer.FertilizerResponse;
import com.xplug.tech.period.PeriodResponse;
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
public class CropFertilizerScheduleTaskResponse {

    private Long id;

    private FertilizerResponse fertilizer;

    private PeriodResponse stageOfGrowth;

    private PeriodResponse applicationInterval;  //period data-type

    private Integer rate;

    private FertilizerApplicationMethod applicationMethod; //enum

    private String remarks;

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    private TaskStatus taskStatus;

    private LocalDateTime taskDate;

    public static CropFertilizerScheduleTaskResponse of(CropFertilizerScheduleTask cropFertilizerScheduleTask) {
        Objects.requireNonNull(cropFertilizerScheduleTask, "CropFertilizerScheduleTask cannot be null!");
        return CropFertilizerScheduleTaskResponse.builder()
                .id(cropFertilizerScheduleTask.getId())
                .fertilizer(FertilizerResponse.of(cropFertilizerScheduleTask.getCropFertilizerSchedule().getFertilizer()))
                .stageOfGrowth(PeriodResponse.of(cropFertilizerScheduleTask.getCropFertilizerSchedule().getStageOfGrowth()))
                .applicationInterval(PeriodResponse.of(cropFertilizerScheduleTask.getCropFertilizerSchedule().getApplicationInterval()))
                .rate(cropFertilizerScheduleTask.getCropFertilizerSchedule().getRate())
                .applicationMethod(cropFertilizerScheduleTask.getCropFertilizerSchedule().getApplicationMethod())
                .remarks(cropFertilizerScheduleTask.getCropFertilizerSchedule().getRemarks())
                .isCompleted(cropFertilizerScheduleTask.getIsCompleted())
                .completionDate(cropFertilizerScheduleTask.getCompletionDate())
                .taskRemarks(cropFertilizerScheduleTask.getTaskRemarks())
                .taskDate(cropFertilizerScheduleTask.getTaskDate())
                .taskStatus(cropFertilizerScheduleTask.getTaskStatus())
                .build();
    }

}
