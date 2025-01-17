package com.xplug.tech.crop.management.domain;

import com.xplug.tech.crop.management.enums.PeriodUnit;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodUnit periodUnit;

    @Column(nullable = false)
    private Integer periodValue;

    public static int toDays(Period period) {
        switch (period.getPeriodUnit()) {
            case DAYS:
                return period.getPeriodValue();
            case WEEKS:
                return period.getPeriodValue() * 7;
            case MONTHS:
                // Assuming 30 days in a month for simplicity
                return period.getPeriodValue() * 30;
            default:
                throw new IllegalArgumentException("Invalid period unit: " + period.getPeriodUnit());
        }
    }

}
