package org.maj.CityCardCore.controller;

import jakarta.websocket.server.PathParam;
import org.joda.time.DateTime;
import org.maj.CityCardCore.dto.OutgoingTicketDto;
import org.maj.CityCardCore.dto.TicketDto;
import org.maj.CityCardCore.model.SeasonTicket;
import org.maj.CityCardCore.model.Ticket;
import org.maj.CityCardCore.repository.TicketRepository;
import org.maj.CityCardCore.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
//@RequestMapping(path = "/season")
public class TicketController {
    /*DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime startDateTime = DateTime.parse(ticket.getStart(), formatter);
                DateTime endDateTime = DateTime.parse(ticket.getEnd(), formatter);
                DateTime currentTime = DateTime.parse(now, formatter);*/

    @Autowired
    public TicketRepository repository;

    @Autowired
    public TicketService ticketService;


    @GetMapping("/check")
    public ResponseEntity<Object> checkIfValid(@PathParam("id") Long ticketId, @PathParam("vehicleId") Long vehicleId, @PathParam("now") String now) {
        try {
            Optional<Ticket> optionalTicket = repository.findById(ticketId);
            LocalDateTime timestamp = LocalDateTime.parse(now);
            if (optionalTicket.isPresent()) {
                return new ResponseEntity<>(optionalTicket.get().isValid(vehicleId, timestamp), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Id is not a valid number", HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<OutgoingTicketDto>> getAllTickets(){
        try {
            List<Ticket> tickets = new ArrayList<>(repository.findAll());

            if(tickets.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tickets.stream().map(Ticket::toOutgoingTicketDto).toList(), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<List<OutgoingTicketDto>> getAllTickets(@PathVariable("id") Long id){
        System.out.println(id);
        try {
            List<Ticket> tickets = new ArrayList<>(repository.findAll());

            if(tickets.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tickets.stream().filter(ticket -> ticket.getPassenger().getId() == id).map(Ticket::toOutgoingTicketDto).toList(), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id){
        Optional<Ticket> ticketOptional = repository.findById(id);

        if(ticketOptional.isPresent()){
            return new ResponseEntity<>(ticketOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/validate/{id}/{vehicleId}")
    public ResponseEntity<Boolean> validateTicket(@PathVariable Long id, @PathVariable Long vehicleId){
        Optional<Ticket> ticketOptional = repository.findById(id);

        if(ticketOptional.isPresent()){
            return new ResponseEntity<>(ticketService.validateTicket(id,LocalDateTime.now().toString(),vehicleId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/buy")
    public ResponseEntity<Ticket> buyTicket(@RequestBody TicketDto ticketDto){
        Ticket newTicket = ticketService.buyTicket(ticketDto);
        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SeasonTicket> deleteSeasonTicketById(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
