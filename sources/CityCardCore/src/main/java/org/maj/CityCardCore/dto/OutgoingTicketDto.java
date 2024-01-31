package org.maj.CityCardCore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.maj.CityCardCore.enums.Fare;
import org.maj.CityCardCore.enums.TicketType;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OutgoingTicketDto {
    Long id;
    TicketType type;
    Fare fare;
    LocalDateTime start;
    LocalDateTime end;
    Integer duration;
    Long vehicleId;
    LocalDateTime timestamp;
}
