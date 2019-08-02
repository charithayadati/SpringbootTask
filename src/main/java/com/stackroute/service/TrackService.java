package com.stackroute.service;

import com.stackroute.domain.Track;

public interface TrackService {

//abstract methods
   public boolean saveTrack(Track track);

    public boolean deleteTrack(int id);

    public String getAllTracks() throws Exception;

    public boolean UpdateTrack(Track track);

}
