package com.xplug.tech.personelinfo;

import com.xplug.tech.utils.money.Currency;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Embeddable
@Entity
@NamedEntityGraph(name = "banking-graph",
        attributeNodes = {
                @NamedAttributeNode("currency")
        })
public class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String accountName;

    private String accountNumber;

    private String branch;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
