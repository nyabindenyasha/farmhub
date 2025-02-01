package com.xplug.tech.crop;

import com.xplug.tech.usermanager.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "User_account_id", nullable = false)
    private UserAccount userAccount; //farmer

    @ManyToOne
    @JoinColumn(name = "crop_schedule_id", nullable = false)
    private CropSchedule cropSchedule; //crop program to be used

    //todo for testing
    // private LocalDate dateOfTransplant; //to derive crop stages and day of maturity

    private LocalDateTime dateOfTransplant; //to derive crop stages and day of maturity

    private String location;

    private String remarks;

    @PrePersist
    @PreUpdate
    public void adjustTimeZone() {
        ZoneId  zoneId = ZoneId.of("Africa/Harare");
        this.dateOfTransplant = ZonedDateTime.of(this.dateOfTransplant, zoneId).toLocalDateTime();
    }

}
