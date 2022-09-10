package com.challenge.IbmChallenge.controller;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests(){
        return requestService.getAllRequests();
    }
}
