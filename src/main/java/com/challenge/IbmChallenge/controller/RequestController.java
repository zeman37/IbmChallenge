package com.challenge.IbmChallenge.controller;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
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

    @PostMapping("/api/newRequest")
    public void newRequest(@RequestParam(value = "query") String query){
        requestService.saveRequest(new Request(query, LocalDateTime.now()));
    }
}
