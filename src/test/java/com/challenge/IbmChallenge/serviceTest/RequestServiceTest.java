package com.challenge.IbmChallenge.serviceTest;


import com.challenge.IbmChallenge.service.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class RequestServiceTest {

    @Autowired
    private RequestService requestService;


    @Test
    void shouldAiQuery(){
        Assertions.assertNotNull(requestService.returnQuery("Search for gif"));
    }

    @Test
    void shouldReturnLastRequests(){
        Assertions.assertNotNull(requestService.getLastRequests());
    }
}
