package com.quizapp.repository;

import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByCategoryId(Long categoryId);

    List<Quiz> findByCreatedBy(User user);

    List<Quiz> findByIsActiveTrue();

    Long countByCategoryId(Long categoryId);
}
