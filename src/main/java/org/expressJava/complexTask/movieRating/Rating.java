package org.expressJava.complexTask.movieRating;

public class Rating<T extends Number> {
    private final T value;

    public Rating(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
