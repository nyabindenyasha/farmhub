package com.xplug.tech.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.jpa.BaseEntity;
import com.xplug.tech.utils.money.Price;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Price of a ticket is required")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price_value")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "currency_code"))
    })
    private Price price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @NotNull(message = "Event is required!")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_event_ticket_event"))
    private Event event;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ticket_ticket_benefit",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_benefit_id"))
    private Set<TicketBenefit> ticketBenefits;

    public Set<TicketBenefit> getTicketBenefits() {
        if (isNull(ticketBenefits)) {
            ticketBenefits = new HashSet<>();
        }
        return ticketBenefits;
    }

}
