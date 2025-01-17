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
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String family;
    private String genus;
    private String species;
    private String subSpecies;

//    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FertilizerSchedule> fertilizerSchedules;
//
//    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PesticideSchedule> pesticideSchedules;

}
