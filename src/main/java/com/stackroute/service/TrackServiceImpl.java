package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    TrackRepository trackRepository;
    Track track;
    @Autowired
    public  String GET_URL= "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=7d6f2b9fc930e3787b10435ea7b7de4a&format=json";

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public TrackServiceImpl() {

    }
//saves the new track
    @Override
    public boolean saveTrack(Track track) {
        Track savedtrack=trackRepository.save(track);
        return  true;

    }

//deletes the track
    @Override
    public boolean deleteTrack(int id) {
        if(trackRepository.existsById(id))
        {

            trackRepository.delete(track);
            return true;
        }
        else
        {
            return false;
        }
    }
    //returns track by id
    @Override
    public Track getTrackById(int id) {
        return track;
    }
//updates the track
    @Override
    public boolean UpdateTrack(Track track) {
        return true;
    }
//fetches all the tracks
    @Override
    public String getAllTracks ()throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
        return "Get request not worked";
    }
    @Override
	public void getTopTracks() {
//		Api uri
		final String uri = "http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=disco&api_key=4326c000f7df7c81681da5052adc2cf3&format=json";
//	    instantiation
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();

//		get a json object as a String
		String json = restTemplate.getForObject(uri, String.class);

		try {
//			converting string as a json node
			JsonNode rootNode = mapper.readTree(json);
			ArrayNode arrayNode =  (ArrayNode)rootNode.path("tracks").path("track");
			//iterate the JSON array
			for (int i = 0; i < arrayNode.size(); i++) {
				//get a new Track object and fill it with data using setters
				Track track = new Track();
				track.setName(arrayNode.get(i).path("name").asText());
				track.setComment(arrayNode.get(i).path("artist").path("name").asText());
				//save the track to database
				trackRepository.save(track);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}





