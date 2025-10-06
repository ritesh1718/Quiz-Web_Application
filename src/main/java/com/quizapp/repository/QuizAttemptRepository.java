package com.quizapp.repository;

import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUser(User user);

    List<QuizAttempt> findByQuiz(Quiz quiz);

    List<QuizAttempt> findByUserId(Long userId);

    List<QuizAttempt> findByQuizId(Long quizId);

    Optional<QuizAttempt> findTopByUserAndQuizOrderByStartedAtDesc(User user, Quiz quiz);
}
