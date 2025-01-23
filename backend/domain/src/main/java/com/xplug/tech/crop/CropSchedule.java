package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private String source; //agronomist/company

    @Column(length = 500)
    private String remarks;

    @Enumerated(EnumType.STRING)
    private CropScheduleType cropScheduleType;

}
