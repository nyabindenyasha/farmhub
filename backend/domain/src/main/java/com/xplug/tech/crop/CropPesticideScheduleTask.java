package com.xplug.tech.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"cropBatch"})
@NoArgsConstructor
@AllArgsConstructor
public class CropPesticideScheduleTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_pesticide_schedule_id", nullable = false)
    private CropPesticideSchedule cropPesticideSchedule;

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private LocalDateTime taskDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "crop_batch_id", nullable = false)
    private CropBatch cropBatch;

    public String getTaskName() {
        return cropBatch.getCropFarmer().getCrop().getName() + " " + cropPesticideSchedule.getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate();
    }

    @PrePersist
    @PreUpdate
    public void adjustTimeZone() {
        ZoneId zoneId = ZoneId.of("Africa/Harare");
        this.taskDate = ZonedDateTime.of(this.taskDate, zoneId).toLocalDateTime();
    }

}
