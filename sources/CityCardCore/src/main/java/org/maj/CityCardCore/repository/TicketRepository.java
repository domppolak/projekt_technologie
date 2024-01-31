package org.maj.CityCardCore.repository;

import org.maj.CityCardCore.model.SeasonTicket;
import org.maj.CityCardCore.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT s FROM SeasonTicket s where s.passenger.id = :X")
    List<Ticket> getAllTickets(@Param("X") long X);
}
