package com.xplug.tech.event;

import com.xplug.tech.crop.CropFertilizerScheduleTask;
import com.xplug.tech.crop.CropPesticideScheduleTask;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TaskReadyForExecutionEvent extends ApplicationEvent {

    //todo use inheritance

    private final CropFertilizerScheduleTask cropFertilizerScheduleTask;

    private final CropPesticideScheduleTask cropPesticideScheduleTask;

    public TaskReadyForExecutionEvent(Object source, CropFertilizerScheduleTask cropFertilizerScheduleTask, CropPesticideScheduleTask cropPesticideScheduleTask) {
        super(source);
        this.cropFertilizerScheduleTask = cropFertilizerScheduleTask;
        this.cropPesticideScheduleTask = cropPesticideScheduleTask;
    }

}
