package com.stackroute.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Track {
//implementing getter and setter methods for the literals
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}