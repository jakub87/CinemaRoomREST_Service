package cinema.service;

import cinema.model.*;
import cinema.model.DTO.TicketDTO;
import cinema.model.DTO.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private Cinema cinema;

    @Autowired
    public CinemaService(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cinema getCinema() {
        return cinema;
    }


    public ResponseEntity purchaseTicket(TicketDTO seatDTO) {
        for (Seat seat: cinema.getAvailable_seats() ) {
            if (seat.getRow() == seatDTO.getRow() && seat.getColumn() == seatDTO.getColumn()) {
                if (seat.isPurchase()) {
                    return new ResponseEntity(new Response("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
                } else {
                    seat.setPurchase(true);
                    return ResponseEntity.ok(new Purchase(seat.getToken(), new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice())));
                }
            }
        }
        return new ResponseEntity(new Response("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity returnTicket(TokenDTO tokenDTO) {
        for (Seat seat: cinema.getAvailable_seats()) {
            if (seat.getToken().equals(tokenDTO.getToken()) && seat.isPurchase()) {
                seat.setPurchase(false);
                return ResponseEntity.ok(new ReturnTicket(new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice())));
            }
        }
        return new ResponseEntity(new Response("Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    public Stats getStats() {
        int currentIncome = 0;
        int numberOfAvailableSeats = 0;
        int numberOfPurchasedTickets = 0;
        for (Seat seat: cinema.getAvailable_seats()) {
            if (seat.isPurchase()) {
                numberOfPurchasedTickets++;
                currentIncome += seat.getPrice();
            } else {
                numberOfAvailableSeats++;
            }
        }
        return new Stats(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets);
    }
}
