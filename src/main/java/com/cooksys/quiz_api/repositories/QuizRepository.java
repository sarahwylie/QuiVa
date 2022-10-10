package com.cooksys.quiz_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.quiz_api.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {


  // TODO: Do you need any derived queries? If so add them here.

}
