package com.xplug.tech.event;

import com.xplug.tech.crop.CropScheduleTask;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TaskReadyForExecutionEvent extends ApplicationEvent {

    //todo use inheritance

    private final CropScheduleTask cropScheduleTask;

    public TaskReadyForExecutionEvent(Object source, CropScheduleTask cropScheduleTask) {
        super(source);
        this.cropScheduleTask = cropScheduleTask;
    }

}
