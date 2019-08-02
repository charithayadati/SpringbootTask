package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    @Autowired
    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

//maps the savetrack request
   @PostMapping("saveTrack")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity<?> responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

//maps the request to fetch the data
    @GetMapping("Tracks")

    public ResponseEntity<?> getTracks  () throws Exception {
        String response=trackService.getAllTracks();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    // Implementing PUT method
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try{
            trackService.UpdateTrack(track);
            responseEntity=new ResponseEntity("Successfully updated", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    // Implementing DELETE method
    @DeleteMapping(value="/track")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
