package com.example.moviebooking.excpetions;

public class PhoneNumberAlreadyExistsException extends Throwable {
    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
