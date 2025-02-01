package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

//    todo breaking the bidirectional mapping

//    @OneToMany(mappedBy = "cropSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CropFertilizerSchedule> fertilizerScheduleList = new HashSet<>();
//
//
//    @OneToMany(mappedBy = "cropSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CropPesticideSchedule> pesticideScheduleList = new HashSet<>();
//
//    public void addFertilizerSchedule(CropFertilizerSchedule fertilizerSchedule) {
//        fertilizerSchedule.setCropSchedule(this);
//        this.fertilizerScheduleList.add(fertilizerSchedule);
//    }
//
//    public void addPesticideSchedule(CropPesticideSchedule pesticideSchedule) {
//        pesticideSchedule.setCropSchedule(this);
//        this.pesticideScheduleList.add(pesticideSchedule);
//    }
//
//    public Set<CropPesticideSchedule> getPesticideScheduleList() {
//        if (isNull(pesticideScheduleList)) {
//            pesticideScheduleList = new HashSet<>();
//        }
//        return pesticideScheduleList;
//    }
//
//    public Set<CropFertilizerSchedule> getFertilizerScheduleList() {
//        if (isNull(fertilizerScheduleList)) {
//            fertilizerScheduleList = new HashSet<>();
//        }
//        return fertilizerScheduleList;
//    }

    @Transient
    private Set<CropFertilizerSchedule> fertilizerScheduleList = new HashSet<>();


    @Transient
    private Set<CropPesticideSchedule> pesticideScheduleList = new HashSet<>();

}
