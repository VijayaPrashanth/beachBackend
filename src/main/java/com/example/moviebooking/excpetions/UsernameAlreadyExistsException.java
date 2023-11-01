package com.example.moviebooking.excpetions;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
