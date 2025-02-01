package com.xplug.tech.utils;

import com.xplug.tech.enums.TaskStatus;

import java.time.LocalDateTime;

public class TaskUtils {

    public static TaskStatus getTaskStatus(LocalDateTime taskDate) {
        LocalDateTime now = LocalDateTime.now();

        //todo check if task is completed after implementing inheritance

//        if (task.getTaskStatus().equals(TaskStatus.IN_PROGRESS) && task.isCompleted) {
//            return TaskStatus.COMPLETED; // If the task is already completed, return COMPLETED status
//        }

        if (taskDate.isBefore(now)) {
            return TaskStatus.OVERDUE; // Task date has passed, but task is not completed
//        } else if (taskDate.isEqual(now) || (taskDate.isAfter(now) && taskDate.isBefore(now.plusHours(1)))) {
        } else if (taskDate.isEqual(now) || (taskDate.isAfter(now) && taskDate.isBefore(now.plusMinutes(5)))) {
            return TaskStatus.IN_PROGRESS; // Task is due soon or currently happening
        } else {
            return TaskStatus.PENDING; // Task is still in the future
        }
    }

}
