package com.challenge.IbmChallenge.controller;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//7th point of 12 Factor App: Export services via port binding
//8th point of 12 Factor App: Scale out via the process model. (Docker + Kubernetes)
//10th point of 12 Factor App: Keep development, staging, and production as similar as possible (Docker)
@RestController
public class RequestController {

    //declaring request service
    private final RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    //API endpoint of getting all requests from database (not implemented in frontend)
    @GetMapping("/api/getAllRequests")
    public List<Request> list(){
        return requestService.getAllRequests();
    }

    //12th point of 12 Factor App: Run admin/management tasks as one-off processes
    @PostMapping("/secret/wipe")
    public String destroy(){
        return requestService.destroy();
    }

    //API endpoint of getting last 5 requests from users (implemented in frontend)
    @GetMapping("/api/getLastRequests")
    public List<Request> getFive(){
        return requestService.getLastRequests();
    }

    //API endpoint which was made for manually adding request to database. Used for testing purposes.

//    @PostMapping("/api/newRequest")
//    public String newRequest(@RequestParam(value = "query") String query){
//        requestService.saveRequest(new Request(query, java.time.LocalDateTime.now()));
//        return "Query for searching: " + query;
//    }

    //API endpoint which returns user's interpreted string by AI
    @GetMapping("/api/getString")
    public String returnQuery(@RequestParam(value = "string") String string){
        requestService.saveRequest(new Request(string, java.time.LocalDateTime.now()));
        return requestService.returnQuery(string);
    }
}

