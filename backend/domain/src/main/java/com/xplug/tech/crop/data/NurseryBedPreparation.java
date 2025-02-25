package com.xplug.tech.crop.data;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NurseryBedPreparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nursery_bed_preparation_id") // Foreign key will be in FertilizerApplicationRate table
    private List<FertilizerApplicationRate> fertilizerApplications;


    @ElementCollection
    @CollectionTable(name = "nursery_bed_preparation_metadata", joinColumns = @JoinColumn(name = "nursery_bed_preparation_id"))
    private List<MetadataEntry> metadataList;

}
