package com.stackroute.MuzixApplication;
import com.stackroute.service.TrackServiceImpl;

public class Call {
    public static void main(String[] args) {
    TrackServiceImpl trackService=new TrackServiceImpl();
    try{
        String  response =trackService.getAllTracks();
        System.out.println(response);
    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
