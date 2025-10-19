package org.expressJava.task12.ratingsMovie;

import java.util.HashMap;
import java.util.List;

public class MovieService {
    HashMap<Movie, List<Rating<? extends Number>>> listMovie = new HashMap<>();

    public synchronized void addRating(Rating<? extends Number> rating) {
        if (rating.getValue().doubleValue() < 1 || rating.getValue().doubleValue() > 10) {
            throw new IllegalArgumentException("Оценка должна быть от 1 до 10");
        }
    }

    }
