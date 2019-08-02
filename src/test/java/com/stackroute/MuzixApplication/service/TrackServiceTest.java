package com.stackroute.MuzixApplication.service;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track;

    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setName("John");
        track.setId(101);
        track.setComment("Jenny");
        list = new ArrayList<>();
        list.add(track);

    }
    @Test
    public void deleteTrack() throws Exception {
        when(trackService.deleteTrack(anyInt())).thenReturn(true);
        Boolean result = trackService.deleteTrack(22);
        Assert.assertEquals(true, result);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Boolean result=trackService.saveTrack(track);
        Assert.assertEquals(true,result );

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }


    @Test
    public void updateTrack() throws Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);

        Boolean result=trackService.saveTrack(track);
        Assert.assertEquals(true,result );

    }

    @Test
    public void getAllTracksFailure() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        String userlist = null;
        try {
            userlist = trackService.getAllTracks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals(list, userlist);
    }

}

