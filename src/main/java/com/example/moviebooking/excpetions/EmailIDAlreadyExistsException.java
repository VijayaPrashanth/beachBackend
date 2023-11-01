package com.example.moviebooking.excpetions;

public class EmailIDAlreadyExistsException extends Throwable {
    public EmailIDAlreadyExistsException(String message) {
        super(message);
    }
}
