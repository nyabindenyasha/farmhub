package com.xplug.tech.jobs;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class SampleRecurringJob {

    private final JobScheduler jobScheduler;

    private final TaskStatusUpdateService taskStatusUpdateService;

    public SampleRecurringJob(JobScheduler jobScheduler, TaskStatusUpdateService taskStatusUpdateService) {
        this.jobScheduler = jobScheduler;
        this.taskStatusUpdateService = taskStatusUpdateService;
    }

    // Using @PostConstruct to schedule jobs on startup
    @PostConstruct
    public void scheduleJobs() {
        // Example 1: Run every 5 minute
        jobScheduler.scheduleRecurrently(
                "five-minutely-task",           // unique identifier
                Cron.every5minutes(),             // cron expression for every minute
                () -> processMinutelyTask() // the job to execute
        );

        // Example 2: Run every hour
        jobScheduler.scheduleRecurrently(
                "hourly-task",
                Cron.hourly(), // same as "0 0 * * * *"
                () -> processHourlyTask()
        );

        // Example 3: Run daily at midnight
        jobScheduler.scheduleRecurrently(
                "daily-task",
                Cron.daily(), // same as "0 0 0 * * *"
                () -> processDailyTask()
        );
    }


    @Job(name = "Process Minutely Task")
    public void processMinutelyTask() {
        System.out.println("5 Minutely task executed at: " + LocalDateTime.now());
        // Your minutely task logic here
        // For example: Check for new messages in a queue
        taskStatusUpdateService.updateTaskStatuses();
    }

    @Job(name = "Process Hourly Task")
    public void processHourlyTask() {
        System.out.println("Hourly task executed at: " + LocalDateTime.now());
        // Your hourly task logic here
        // For example: Generate hourly reports
    }

    @Job(name = "Process Daily Task")
    public void processDailyTask() {
        System.out.println("Daily task executed at: " + LocalDateTime.now());
        // Your daily task logic here
        // For example: Daily data cleanup
    }

}
