package com.xplug.tech.events;

import com.xplug.tech.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private String owner;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String fileUrl;

    @NotNull
    @Column(nullable = false)
    private String location;

    @NotNull
    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @NotNull(message = "Event Category is required!")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_event_event_category"))
    private EventCategory eventCategory;

    @NotNull(message = "Event Type is required!")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_event_event_type"))
    private EventType eventType;

    private Long totalTickets;

}
