package org.maj.CityCardCore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.maj.CityCardCore.dto.OutgoingTicketDto;
import org.maj.CityCardCore.enums.Fare;
import org.maj.CityCardCore.enums.TicketType;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TimedTicket extends Ticket{

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "time", nullable = false)
    private Integer timeInMin;


    private LocalDateTime end(){
        return start==null?null:start.plusMinutes(timeInMin);
    }

    public TimedTicket(Fare fare, User p, int duration) {
        this.fare = fare;
        this.passenger = p;
        this.timeInMin = duration;
    }

    @Override
    public TicketType type() {
        return TicketType.TIMED;
    }

    @Override
    public boolean isValid(Long vehicleId, LocalDateTime timestamp) {
        return timestamp.isAfter(start) && timestamp.isBefore(start.plusMinutes(timeInMin));
    }

    @Override
    public boolean validate(Long vehicleId, LocalDateTime timestamp) {
        if(start == null) {
            start = timestamp;
            return true;
        }
        else return false;
    }

    @Override
    public OutgoingTicketDto toOutgoingTicketDto() {
        return new OutgoingTicketDto(getId(),type(),fare,start,end(),timeInMin,null,null);
    }
}
