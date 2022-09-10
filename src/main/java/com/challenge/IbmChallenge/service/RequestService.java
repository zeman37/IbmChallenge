package com.challenge.IbmChallenge.service;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request saveRequest(Request request){
        return requestRepository.save(request);
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

}
