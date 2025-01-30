package com.xplug.tech.crop;

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

    @OneToOne
    @JoinColumn(name = "crop_schedule_id", nullable = false)
    private CropSchedule cropSchedule;

    @OneToMany(mappedBy = "cropSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CropPesticideSchedule> pesticideScheduleList;

    @OneToMany(mappedBy = "cropSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
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
