package org.expressJava.task12.ratingsMovie;

public class Rating<T extends Number> {
    private T value;

    public Rating(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
