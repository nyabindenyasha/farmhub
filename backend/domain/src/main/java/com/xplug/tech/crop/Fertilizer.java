package com.xplug.tech.crop;

import com.xplug.tech.enums.FertilizerType;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FertilizerType type;

}
