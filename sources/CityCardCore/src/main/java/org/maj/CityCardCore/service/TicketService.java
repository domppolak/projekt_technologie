package org.maj.CityCardCore.service;

import org.maj.CityCardCore.dto.TicketDto;
import org.maj.CityCardCore.model.*;
import org.maj.CityCardCore.repository.TicketRepository;
import org.maj.CityCardCore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    
    public Ticket buyTicket(TicketDto ticketDto){
        User p = userRepository.findById(ticketDto.getPassengerId()).get();
        Ticket t = switch(ticketDto.getType()){
            case SINGLE -> new SingleTicket(ticketDto.getFare(),p);
            case SEASON -> new SeasonTicket(ticketDto.getFare(),p,ticketDto.getStart(),ticketDto.getEnd());
            case TIMED -> new TimedTicket(ticketDto.getFare(),p,ticketDto.getDuration());
        };
        System.out.println(t);
        ticketRepository.save(t);
        return t;
    }

    public boolean validateTicket(Long id, String currentTime, Long vehicleId){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            var t = ticket.get();
            var result = t.validate(vehicleId, LocalDateTime.parse(currentTime));
            ticketRepository.save(t);
            return result;
        }else{
            throw new NoSuchElementException("Ticket with given id does not exist");
        }
    }
}
