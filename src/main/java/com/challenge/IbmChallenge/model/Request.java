package com.challenge.IbmChallenge.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "request")
    private String request;
    @Column(name = "request_date")
    private LocalDateTime date;

    public Request() {
    }
}
