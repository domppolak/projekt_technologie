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
public class SingleTicket extends Ticket{

    public SingleTicket(Fare fare, User passenger){
        this.fare = fare;
        this.passenger = passenger;
    }

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Override
    public TicketType type() {
        return TicketType.SINGLE;
    }

    @Override
    public boolean isValid(Long vehicleId, LocalDateTime timestamp) {
        return this.vehicleId == vehicleId;
    }

    @Override
    public boolean validate(Long vehicleId, LocalDateTime timestamp) {
        if (this.vehicleId != null) return false;
        else{
            this.vehicleId = vehicleId;
            return true;
        }
    }

    @Override
    public OutgoingTicketDto toOutgoingTicketDto() {
        return new OutgoingTicketDto(getId(),type(),fare,null,null,null,vehicleId,null);
    }
}
