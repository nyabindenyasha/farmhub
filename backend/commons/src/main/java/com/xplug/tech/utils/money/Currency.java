package com.xplug.tech.utils.money;

import com.xplug.tech.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private String code;

}
