package com.xplug.tech.crop.data;

import com.xplug.tech.crop.Crop;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //todo one-to-one
    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop classification;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nursery_management_id", nullable = false)
    private CropNurseryManagement nurseryManagement;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "field_management_id", nullable = false)
    private CropFieldManagement fieldManagement;

}

