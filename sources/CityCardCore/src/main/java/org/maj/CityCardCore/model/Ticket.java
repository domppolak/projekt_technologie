package org.maj.CityCardCore.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.maj.CityCardCore.dto.OutgoingTicketDto;
import org.maj.CityCardCore.enums.Fare;
import org.maj.CityCardCore.enums.TicketType;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    protected User passenger;

    public abstract TicketType type();

    @Getter
    protected Fare fare;

    public abstract boolean isValid(Long vehicleId, LocalDateTime timestamp);

    public abstract boolean validate(Long vehicleId, LocalDateTime timestamp);

    public abstract OutgoingTicketDto toOutgoingTicketDto();
}
