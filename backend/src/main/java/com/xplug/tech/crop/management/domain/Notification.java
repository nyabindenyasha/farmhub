package com.xplug.tech.crop.management.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    private String eventType; // e.g., "Fertilizer Application", "Spraying", "Harvest Ready", "Harvest Ended"
    private LocalDateTime eventDate;
    private LocalDateTime notificationDate;
    private Boolean sent;

}
