package com.challenge.IbmChallenge.repository;


import com.challenge.IbmChallenge.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//4th point of 12 Factor App: Treat backing services as attached resources.
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    //function which finds last 5 records from database
    List<Request> findFirst5ByOrderByIdDesc();
}
