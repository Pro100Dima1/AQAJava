package org.expressJava.task12.ratingsMovie;

import org.expressJava.task10.ex10.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService<T extends Number> {
    private final HashMap<Movie, List<Rating<T>>> listMovie = new HashMap<>();

    public synchronized void addRating(Movie movie, Rating<T> rating) {
        if (rating.getValue().doubleValue() < 1 || rating.getValue().doubleValue() > 10) {
            throw new IllegalArgumentException("Оценка должна быть от 1 до 10");
        }
        listMovie.putIfAbsent(movie, new ArrayList<>());
        listMovie.get(movie).add(rating);
    }

    public double getAverageRating(Movie movie) {
        List<Rating<T>> movieRatings = listMovie.get(movie);
        if (movieRatings == null || movieRatings.isEmpty()) {
            throw new IllegalArgumentException("Нет оценок для фильма");
        }
        return movieRatings.stream().mapToDouble(r -> r.getValue().doubleValue()).average().orElse(0.0);
    }

    public List<Movie> getSortedMoviesByRating() {
        return listMovie.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .sorted((e1, e2) -> Double.compare(
                        average(e2.getValue()),
                        average(e1.getValue())
                ))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double average(List<Rating<T>> ratings) {
        return ratings.stream().mapToDouble(r -> r.getValue().doubleValue())
                .average()
                .orElse(0.0);
    }
}
