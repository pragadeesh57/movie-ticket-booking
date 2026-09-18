package movie_ticket_booking.controller;

import movie_ticket_booking.model.Booking;
import movie_ticket_booking.model.Movie;
import movie_ticket_booking.repository.BookingRepository;
import movie_ticket_booking.repository.MovieRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class BookingController {

    private final MovieRepository movieRepository;
    private final BookingRepository bookingRepository;

    public BookingController(MovieRepository movieRepository,
                             BookingRepository bookingRepository) {

        this.movieRepository = movieRepository;
        this.bookingRepository = bookingRepository;
    }

    // Open booking page
    @GetMapping("/book/{id}")
    public String showBookingPage(@PathVariable Long id, Model model) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        model.addAttribute("movie", movie);
        model.addAttribute("booking", new Booking());

        return "book";
    }

    // Save booking
    @PostMapping("/book")
    public String bookTicket(@RequestParam Long movieId,
                             @RequestParam String customerName,
                             @RequestParam String seatNumber) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Booking booking = new Booking();

        booking.setMovie(movie);
        booking.setCustomerName(customerName);
        booking.setSeatNumber(seatNumber);
        booking.setBookingDate(LocalDateTime.now());

        bookingRepository.save(booking);

        return "redirect:/booking-success";
    }

    // Success page
    @GetMapping("/booking-success")
    public String bookingSuccess() {
        return "booking-success";
    }
}