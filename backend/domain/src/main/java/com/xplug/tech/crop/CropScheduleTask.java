package com.xplug.tech.crop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
@Getter
@Setter
@ToString(exclude = {"cropBatch"})
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class CropScheduleTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private LocalDateTime taskDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "crop_program_id") // Prevent ownership
    private CropBatch cropBatch;

    @PrePersist
    @PreUpdate
    public void adjustTimeZone() {
        ZoneId zoneId = ZoneId.of("Africa/Harare");
        this.taskDate = ZonedDateTime.of(this.taskDate, zoneId).toLocalDateTime();
    }

    public String getTaskName() {
        return " ";
    }

}
