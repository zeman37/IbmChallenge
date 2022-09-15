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
    //Introducing Watson Natural Language Understanding service
    IamAuthenticator authenticator = new IamAuthenticator.Builder().apikey(System.getenv("APIKEY")).build();
    NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding(java.time.LocalDate.now().toString(),authenticator);

    //Declaring repository
    @Autowired
    private RequestRepository requestRepository;

    public Request saveRequest(Request request){
        return requestRepository.save(request);
    }

    //Method which return all requests from database
    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    //Method which returns last 5 requests from database
    public List<Request> getLastRequests(){
        return requestRepository.findFirst5ByOrderByIdDesc();
    }

    //12th point of 12 Factor App: Run admin/management tasks as one-off processes
    public String destroy(){
        requestRepository.deleteAll(); //method which deletes all records from database.
        return "All records succesfully deleted.";
    }

    //Method which returns user's query interpreted by Watson Natural Language AI
    public String returnQuery(String text){
        naturalLanguageUnderstanding.setServiceUrl("https://api.eu-gb.natural-language-understanding.watson.cloud.ibm.com/instances/c29555f9-3f30-46ce-8240-4e701cb21b8c");
        String requestText = "";

        //Since my choice will be keywords and concepts, initializing their classes.

        KeywordsOptions keywords= new KeywordsOptions.Builder()
                .emotion(true)
                .limit(3) //limiting to 3 keywords per user input string
                .build();

        ConceptsOptions conceptsOptions = new ConceptsOptions.Builder().limit(3).build(); //limiting to 3 concepts per user input string

        Features features = new Features.Builder()
                .keywords(keywords).concepts(conceptsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .language("en")
                .build(); //building analyze options which include our features, text and language - English

        AnalysisResults response = naturalLanguageUnderstanding
                .analyze(parameters)
                .execute()
                .getResult(); //getting results

        try{
            requestText += response.getConcepts().get(0).getText() + " "; //Getting first concept as an interpreted text
        } catch (IndexOutOfBoundsException ignored){} //If IndexOutOfBoundsException (concept text too long), do nothing.

        if(requestText.equals("")){ //if request text is empty
            for(KeywordsResult k : response.getKeywords()){
                requestText += k.getText() + " "; //getting all keywords as interpreted text
            }
        } else{
            try {
                requestText += response.getKeywords().get(0).getText(); //if request text is not empty, adding first keyword, so that the query will be more specified/interpreted (concept + one keyword)
            } catch (IndexOutOfBoundsException ignored){} //If IndexOutOfBoundsException (concept text too long), do nothing.
        }

        if(requestText.equals("")){
            requestText = "blue screen pokemon"; //if AI does not interpret our string, return pokemon, which shows "404" error as a gif:)
        }

        System.out.println("==========================================" + requestText + "=========================================="); //printing interpreted text
        System.out.println(response); //printing whole AI response

        return requestText;
    }

}
