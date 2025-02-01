package com.xplug.tech.enums;

public enum TaskStatus {
    PENDING,  // stage not yet arrived
    IN_PROGRESS, // stage has arrived, not yet overdue
    COMPLETED, // stage has arrived, and task executed
    OVERDUE // stage has arrived, and task not executed
}
