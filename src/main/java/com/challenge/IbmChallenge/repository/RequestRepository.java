package com.challenge.IbmChallenge.repository;


import com.challenge.IbmChallenge.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

}
