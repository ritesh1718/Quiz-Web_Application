package com.quizapp.repository;

import com.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Get all questions for a quiz
    List<Question> findByQuizId(Long quizId);

    // Get questions with their options (eager fetch)
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answerOptions WHERE q.quiz.id = :quizId")
    List<Question> findByQuizIdWithOptions(Long quizId);

    // Count questions in a quiz
    Long countByQuizId(Long quizId);
}
