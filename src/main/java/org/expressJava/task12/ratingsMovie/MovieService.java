package org.expressJava.task12.ratingsMovie;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService<T extends Number> {
    private HashMap<Movie, List<Rating<T>>> listMovie = new HashMap<>();

    public synchronized void addRating(Movie movie, Rating<T> rating) {
        if (rating.getValue().doubleValue() < 1 || rating.getValue().doubleValue() > 10) {
            throw new IllegalArgumentException("Invalid rating");
        }
        listMovie.putIfAbsent(movie, new ArrayList<>());
        listMovie.get(movie).add(rating);
    }

    public List<Movie> getSortedMoviesByRating() {
        return listMovie.entrySet().stream()
                .filter(d -> !d.getValue().isEmpty())
                .sorted((d1, d2) -> Double.compare(
                        average(d2.getValue()),
                        average(d1.getValue())
                ))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public double average(List<Rating<T>> ratings) {
        return ratings.stream()
                .mapToDouble(d -> d.getValue().doubleValue())
                .average()
                .orElse(0);
    }

    public double getAverageRatingMovie(Movie movie) {
        List<Rating<T>> ratings = listMovie.get(movie);
        if (ratings == null || ratings.isEmpty()) {
            throw new IllegalArgumentException("No ratings for this movie");
        }
        return ratings.stream()
                .mapToDouble(d -> d.getValue().doubleValue())
                .average()
                .orElse(0);
    }
}
