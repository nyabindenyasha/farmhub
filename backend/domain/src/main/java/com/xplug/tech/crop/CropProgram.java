package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropProgram {

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

    @OneToMany(mappedBy = "cropProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CropPesticideSchedule> pesticideScheduleList;

    @OneToMany(mappedBy = "cropProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CropFertilizerSchedule> fertilizerScheduleList;

    public Set<CropPesticideSchedule> getPesticideScheduleList() {
        if (isNull(pesticideScheduleList)) {
            pesticideScheduleList = new HashSet<>();
        }
        return pesticideScheduleList;
    }

    public Set<CropFertilizerSchedule> getFertilizerScheduleList() {
        if (isNull(fertilizerScheduleList)) {
            fertilizerScheduleList = new HashSet<>();
        }
        return fertilizerScheduleList;
    }

}
