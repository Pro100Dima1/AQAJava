package org.expressJava.task12.validator;

public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}