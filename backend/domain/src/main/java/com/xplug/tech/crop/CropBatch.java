package com.xplug.tech.crop;

import com.xplug.tech.usermanager.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "User_account_id", nullable = false)
    private UserAccount userAccount; //farmer

    @ManyToOne
    @JoinColumn(name = "crop_program_id", nullable = false)
    private CropProgram cropProgram; //crop program to be used

    //todo for testing
    // private LocalDate dateOfTransplant; //to derive crop stages and day of maturity

    private LocalDateTime dateOfTransplant; //to derive crop stages and day of maturity

    private String location;

    private String remarks;

    // OWN the relationship by adding @JoinColumn on OneToMany
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "crop_batch_id") // Ensures foreign key in CropPesticideSchedule table
    private Set<CropPesticideScheduleTask> pesticideScheduleTasks;

    // OWN the relationship by adding @JoinColumn on OneToMany
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "crop_batch_id") // Ensures foreign key in CropPesticideSchedule table
    private Set<CropFertilizerScheduleTask> fertilizerScheduleTasks;

    public void addPesticideScheduleTask(CropPesticideScheduleTask task) {
        task.setCropBatch(this);
        this.pesticideScheduleTasks.add(task);
    }

    public void addFertilizerScheduleTask(CropFertilizerScheduleTask task) {
        task.setCropBatch(this);
        this.fertilizerScheduleTasks.add(task);
    }

    public Set<CropPesticideScheduleTask> getPesticideScheduleTasks() {
        if (isNull(pesticideScheduleTasks)) {
            pesticideScheduleTasks = new HashSet<>();
        }
        return pesticideScheduleTasks;
    }

    public Set<CropFertilizerScheduleTask> getFertilizerScheduleTasks() {
        if (isNull(fertilizerScheduleTasks)) {
            fertilizerScheduleTasks = new HashSet<>();
        }
        return fertilizerScheduleTasks;
    }

    public List<CropScheduleTask> getCropScheduleTasks() {
        List<CropScheduleTask> cropScheduleTaskList = new ArrayList<>();
        cropScheduleTaskList.addAll(getFertilizerScheduleTasks());
        cropScheduleTaskList.addAll(getPesticideScheduleTasks());
        return cropScheduleTaskList;
    }

}
