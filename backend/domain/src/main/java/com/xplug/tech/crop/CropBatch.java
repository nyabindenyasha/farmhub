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
public class CropBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "crop_farmer_id", nullable = false)
    private CropFarmer cropFarmer;

//  todo breaking the bidirectional mapping

//    @OneToMany(mappedBy = "cropBatch", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CropPesticideScheduleTask> pesticideScheduleTasks = new HashSet<>();
//
//    @OneToMany(mappedBy = "cropBatch", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CropFertilizerScheduleTask> fertilizerScheduleTasks = new HashSet<>();
//
//    public void addPesticideScheduleTask(CropPesticideScheduleTask task) {
//        task.setCropBatch(this);
//        this.pesticideScheduleTasks.add(task);
//    }
//
//    public void addFertilizerScheduleTask(CropFertilizerScheduleTask task) {
//        task.setCropBatch(this);
//        this.fertilizerScheduleTasks.add(task);
//    }
//
//    public Set<CropPesticideScheduleTask> getPesticideScheduleTasks() {
//        if (isNull(pesticideScheduleTasks)) {
//            pesticideScheduleTasks = new HashSet<>();
//        }
//        return pesticideScheduleTasks;
//    }
//
//    public Set<CropFertilizerScheduleTask> getFertilizerScheduleTasks() {
//        if (isNull(fertilizerScheduleTasks)) {
//            fertilizerScheduleTasks = new HashSet<>();
//        }
//        return fertilizerScheduleTasks;
//    }

    @Transient
    private Set<CropFertilizerScheduleTask> fertilizerScheduleTasks = new HashSet<>();


    @Transient
    private Set<CropPesticideScheduleTask> pesticideScheduleTasks = new HashSet<>();

}
