package com.quizapp.repository;

import com.quizapp.entity.UserAnswer;
import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    List<UserAnswer> findByQuizAttempt(QuizAttempt quizAttempt);

    List<UserAnswer> findByQuizAttemptIdOrderByAnsweredAtAsc(Long quizAttemptId);

    List<UserAnswer> findByQuizAttemptAndQuestion(QuizAttempt quizAttempt, Question question);

    Long countByQuizAttempt(QuizAttempt quizAttempt);
}
