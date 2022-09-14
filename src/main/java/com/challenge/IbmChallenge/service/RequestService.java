package com.challenge.IbmChallenge.service;

import com.challenge.IbmChallenge.model.Request;
import com.challenge.IbmChallenge.repository.RequestRepository;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    IamAuthenticator authenticator = new IamAuthenticator.Builder().apikey(System.getenv("APIKEY")).build();
    NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding(java.time.LocalDate.now().toString(),authenticator);

    @Autowired
    private RequestRepository requestRepository;

    public Request saveRequest(Request request){
        return requestRepository.save(request);
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    public List<Request> getLastRequests(){
        return requestRepository.findFirst5ByOrderByIdDesc();
    }

    public String returnQuery(String text){
        naturalLanguageUnderstanding.setServiceUrl("https://api.eu-gb.natural-language-understanding.watson.cloud.ibm.com/instances/c29555f9-3f30-46ce-8240-4e701cb21b8c");
        String requestText = "";

        KeywordsOptions keywords= new KeywordsOptions.Builder()
                .emotion(true)
                .limit(3)
                .build();

        ConceptsOptions conceptsOptions = new ConceptsOptions.Builder().limit(3).build();

        Features features = new Features.Builder()
                .keywords(keywords).concepts(conceptsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .language("en")
                .build();

        AnalysisResults response = naturalLanguageUnderstanding
                .analyze(parameters)
                .execute()
                .getResult();

        try{
            requestText += response.getConcepts().get(0).getText() + " ";
        } catch (IndexOutOfBoundsException e){}

        if(requestText.equals("")){
            for(KeywordsResult k : response.getKeywords()){
                requestText += k.getText() + " ";
            }
        } else{
            try {
                requestText += response.getKeywords().get(0).getText();
            } catch (IndexOutOfBoundsException e){}
        }

        if(requestText.equals("")){
            requestText = "blue screen pokemon";
        }

        System.out.println("==========================================" + requestText + "==========================================");
        System.out.println(response);

        return requestText;
    }

}
