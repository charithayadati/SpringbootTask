package com.stackroute.exceptions;

public class TrackNotFound extends Exception {
    String message;

    public TrackNotFound() {
    }

    public TrackNotFound(String message) {
        super(message);
        this.message = message;
    }
}
