package org.expressJava.task12.inventory;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}
