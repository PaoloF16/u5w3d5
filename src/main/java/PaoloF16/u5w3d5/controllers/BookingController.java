package PaoloF16.u5w3d5.controllers;

import PaoloF16.u5w3d5.entities.Booking;
import PaoloF16.u5w3d5.entities.User;
import PaoloF16.u5w3d5.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking bookEvent(@PathVariable Long eventId, @AuthenticationPrincipal User currentUser) {
        return bookingService.bookEvent(currentUser.getId(), eventId);
    }

    @GetMapping("/me")
    public List<Booking> getMyBookings(@AuthenticationPrincipal User currentUser) {
        return bookingService.getBookingsByUser(currentUser.getId());
    }


}
