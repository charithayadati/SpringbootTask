package com.stackroute.exceptions;

//customized exception for the existing track
public class TrackAlreadyExistsException extends Exception {
    String message;

    public TrackAlreadyExistsException() {
    }

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}