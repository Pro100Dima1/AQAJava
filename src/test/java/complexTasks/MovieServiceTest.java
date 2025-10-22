package complexTasks;

import org.expressJava.task12.ratingsMovie.Movie;
import org.expressJava.task12.ratingsMovie.MovieService;
import org.expressJava.task12.ratingsMovie.Rating;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieServiceTest {

    @Test
    void testAddAndAverageRating() {
        MovieService<Integer> service = new MovieService<>();
        Movie movie = new Movie("Inception");
        service.addRating(movie, new Rating<>(9));
        service.addRating(movie, new Rating<>(7));
        assertEquals(8.0, service.getAverageRating(movie));
    }

    @Test
    void testAddInvalidRating() {
        MovieService<Integer> service = new MovieService<>();
        Movie movie = new Movie("Bad Movie");
        assertThrows(IllegalArgumentException.class, () -> service.addRating(movie, new Rating<>(11)));
    }

    @Test
    void testSortedMoviesByRating() {
        MovieService<Double> service = new MovieService<>();
        Movie m1 = new Movie("Movie A");
        Movie m2 = new Movie("Movie B");
        service.addRating(m1, new Rating<>(6.0));
        service.addRating(m2, new Rating<>(9.0));
        List<Movie> sorted = service.getSortedMoviesByRating();
        assertEquals(m2, sorted.get(0));
        assertEquals(m1, sorted.get(1));
    }

    @Test
    void testAverageWithoutRatings() {
        MovieService<Integer> service = new MovieService<>();
        Movie movie = new Movie("Empty");
        assertThrows(IllegalArgumentException.class, () -> service.getAverageRating(movie));
    }
}
