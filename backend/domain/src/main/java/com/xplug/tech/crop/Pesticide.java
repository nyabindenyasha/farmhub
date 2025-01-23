package com.xplug.tech.crop;

import com.xplug.tech.enums.PesticideModeOfAction;
import com.xplug.tech.enums.PesticideType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pesticide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String alias;

    @ElementCollection
    @CollectionTable(name = "pesticide_active_ingredients", joinColumns = @JoinColumn(name = "pesticide_id"))
    private List<String> activeIngredients;

    private String applicationRate;  //pack unit function of pack unit per knapsack

    private Integer safetyInterval;

    @Enumerated(EnumType.STRING)
    private PesticideType pesticideType;

    @Enumerated(EnumType.STRING)
    private PesticideModeOfAction modeOfAction;

    @ElementCollection
    @CollectionTable(name = "pesticide_target_pests", joinColumns = @JoinColumn(name = "pesticide_id"))
    private List<String> targetPests;

    @ElementCollection
    @CollectionTable(name = "pesticide_target_diseases", joinColumns = @JoinColumn(name = "pesticide_id"))
    private List<String> targetDiseases;

}
