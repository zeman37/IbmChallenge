package com.challenge.IbmChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//6th point of 12 Factor App: Execute the app as one or more stateless processes.
//9th point of 12 Factor App: Maximize robustness with fast startup and graceful shutdown
@SpringBootApplication
public class IbmChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmChallengeApplication.class, args);
	}

}
