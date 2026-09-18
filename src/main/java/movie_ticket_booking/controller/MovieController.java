package movie_ticket_booking.controller;

import movie_ticket_booking.model.Movie;
import movie_ticket_booking.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Show all movies
    @GetMapping("/movies")
    public String showMovies(Model model) {

        List<Movie> movies = movieRepository.findAll();

        model.addAttribute("movies", movies);

        return "movies";
    }

    // Show Add Movie page
    @GetMapping("/add-movie")
    public String showAddMovieForm(Model model) {

        model.addAttribute("movie", new Movie());

        return "add-movie";
    }

    // Save movie
    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute Movie movie) {

        movieRepository.save(movie);

        return "redirect:/movies";
    }
}