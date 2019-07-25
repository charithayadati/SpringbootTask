package com.stackroute.userservice;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {


    public boolean saveTrack(Track track);

    public boolean deleteTrack(int id);

    public List<Track> getAllTracks();

    public Track getTrackById(int id);

    public boolean UpdateTrack(Track track);
}
