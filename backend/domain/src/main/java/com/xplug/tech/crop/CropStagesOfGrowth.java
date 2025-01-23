package com.xplug.tech.crop;

import com.xplug.tech.enums.StageOfGrowth;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropStagesOfGrowth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "stage_start_date_period_id", nullable = false)
    private Period stageStartDate;

    @ManyToOne
    @JoinColumn(name = "stage_end_date_period_id", nullable = false)
    private Period stageEndDate;  //upper date, not end of stage

    @Enumerated(EnumType.STRING)
    private StageOfGrowth stageOfGrowth;

}
