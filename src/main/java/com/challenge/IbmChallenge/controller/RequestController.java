package com.challenge.IbmChallenge.controller;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*") //for testing purposes
public class RequestController {

    private final RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/api/getAllRequests")
    public List<Request> list(){
        return requestService.getAllRequests();
    }

    @GetMapping("/api/getLastRequests")
    public List<Request> getFive(){
        return requestService.getLastRequests();
    }

    @PostMapping("/api/newRequest")
    public String newRequest(@RequestParam(value = "query") String query){
        requestService.saveRequest(new Request(query, java.time.LocalDateTime.now()));
        return "Query for searching: " + query;
    }

    @GetMapping("/api/getString")
    public String returnQuery(@RequestParam(value = "string") String string){
        requestService.saveRequest(new Request(string, java.time.LocalDateTime.now()));
        return requestService.returnQuery(string);
    }
}

