package com.stackroute.exceptions;

public class TrackAlreadyExists extends Exception{
    String message;

    public TrackAlreadyExists() {
    }

    public TrackAlreadyExists(String message) {
        super(message);
        this.message = message;
    }
}
