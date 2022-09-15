package com.challenge.IbmChallenge.serviceTest;


import com.challenge.IbmChallenge.service.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties") //test class which use H2-in-memory database (for tests) which is described in application-test.properties
public class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    //Since our back-end application needs to return interpreted string by AI, this test checks whether not empty string is returned when "user" inputs: "search for gif"
    @Test
    void shouldAiQuery(){
        Assertions.assertNotNull(requestService.returnQuery("Search for gif"));
    }

    //Checking whether records can be accessed from database.
    @Test
    void shouldReturnLastRequests(){
        Assertions.assertNotNull(requestService.getLastRequests());
    }
}
