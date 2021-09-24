package cinema.controler;

import cinema.model.Cinema;
import cinema.model.DTO.TicketDTO;
import cinema.model.DTO.TokenDTO;
import cinema.model.Response;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CinemaControler {

    private CinemaService cinemaService;

    @Autowired
    public CinemaControler(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinemaService.getCinema();
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseSeat(@RequestBody TicketDTO seatDTO) {
        return cinemaService.purchaseTicket(seatDTO);
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody TokenDTO tokenDTO) {
        return  cinemaService.returnTicket(tokenDTO);
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam Optional<String> password)
    {
       if (password.isPresent() && password.get().equals("super_secret")) {
           return ResponseEntity.ok(cinemaService.getStats());
       } else {
           return new ResponseEntity(new Response("The password is wrong!"), HttpStatus.UNAUTHORIZED);
       }
    }
}
