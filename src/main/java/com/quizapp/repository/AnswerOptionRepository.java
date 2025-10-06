package com.quizapp.repository;

import com.quizapp.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {

    // Get all options for a question ordered
    List<AnswerOption> findByQuestionIdOrderByOptionOrderAsc(Long questionId);

    // Get all correct options for a question
    List<AnswerOption> findByQuestionIdAndIsCorrectTrue(Long questionId);
}
