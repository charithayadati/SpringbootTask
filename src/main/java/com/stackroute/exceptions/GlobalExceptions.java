package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptions extends RuntimeException
{
    // Handling TrackalreadyExist exception using globally at runtime
    @ExceptionHandler(TrackAlreadyExists.class)
    public ResponseEntity<String> saveTrackException()
    {
        return new ResponseEntity<String>("Runtime Exception TrackAlreadyExist", HttpStatus.CONFLICT);
    }

    // Handling TrackNotFoundException using globally at runtime
    @ExceptionHandler(TrackNotFound.class)
    public ResponseEntity<String> updateTrackException()
    {
        return new ResponseEntity<String>("Runtime Exception TrackNotFound", HttpStatus.CONFLICT);
    }

}
