package movie_ticket_booking.repository;

import movie_ticket_booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}