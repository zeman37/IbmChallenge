package com.challenge.IbmChallenge.model;

import javax.persistence.*;
import java.time.LocalDateTime;

//declaring our entity, which will go into database
@Entity
@Table(name = "requests") //specifying database name
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID goes auto generated
    @Column(name = "id")
    private int id;
    @Column(name = "request")
    private String request;
    @Column(name = "request_date")
    private LocalDateTime date;

    public Request(String request, LocalDateTime date) {
        this.request = request;
        this.date = date;
    }

    public Request() {

    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
