package com.xplug.tech.crop.management.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropVariety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    private String variety;

    private Integer maturityStartDay; //days range

    private Integer maturityEndDay;

    private Integer harvestDuration; //harvest window in days

    private String remarks;

}
