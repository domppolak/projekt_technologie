package org.maj.CityCardCore.model;

import jakarta.persistence.*;
import lombok.*;
import org.maj.CityCardCore.dto.OutgoingTicketDto;
import org.maj.CityCardCore.enums.Fare;
import org.maj.CityCardCore.enums.TicketType;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SeasonTicket extends Ticket {

    private LocalDateTime start;

    private LocalDateTime end;

    protected TicketType type = TicketType.SEASON;

    public SeasonTicket(Fare fare, User p, LocalDateTime start, LocalDateTime end) {
        this.fare = fare;
        this.passenger = p;
        this.start = start;
        this.end = end;
    }

    @Override
    public TicketType type() {
        return TicketType.SEASON;
    }

    @Override
    public boolean isValid(Long vehicleId, LocalDateTime timestamp) {
        return timestamp.isAfter(start) && timestamp.isBefore(end);
    }

    @Override
    public boolean validate(Long vehicleId, LocalDateTime timestamp) {
        return isValid(vehicleId, timestamp);
    }
    private LocalDate toLocalDate(LocalDateTime dt){
        return dt == null?null:dt.toLocalDate();
    }

    @Override
    public OutgoingTicketDto toOutgoingTicketDto() {
        return new OutgoingTicketDto(getId(),type(),fare,start,end,null,null,null);
    }
}
