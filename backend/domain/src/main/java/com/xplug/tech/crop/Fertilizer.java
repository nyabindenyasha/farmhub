package com.xplug.tech.crop;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String alias;

    private String composition;

    @Column(length = 500)
    private String remarks;

//    @OneToMany(mappedBy = "fertilizer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FertilizerSchedule> schedules;

}
