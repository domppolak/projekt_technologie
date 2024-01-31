package org.maj.CityCardCore.dto;

import lombok.Data;
import org.maj.CityCardCore.enums.Fare;
import org.maj.CityCardCore.enums.TicketType;

import java.time.LocalDateTime;

@Data
public class TicketDto {
    Long passengerId;
    LocalDateTime start;
    LocalDateTime end;
    Integer duration;
    Fare fare;
    TicketType type;
}
