package com.quizapp.service;

import com.quizapp.entity.Question;
import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.UserAnswer;
import com.quizapp.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    // Submit an answer
    public UserAnswer submitAnswer(UserAnswer userAnswer) {
        return userAnswerRepository.save(userAnswer);
    }

    public List<UserAnswer> getAnswersByAttempt(QuizAttempt attempt) {
        return userAnswerRepository.findByQuizAttempt(attempt);
    }

    public List<UserAnswer> getAnswersByAttemptOrdered(QuizAttempt attempt) {
        return userAnswerRepository.findByQuizAttemptIdOrderByAnsweredAtAsc(attempt.getId());
    }

    public List<UserAnswer> getAnswersByAttemptAndQuestion(QuizAttempt attempt, Question question) {
        return userAnswerRepository.findByQuizAttemptAndQuestion(attempt, question);
    }

    public Long countAnswersByAttempt(QuizAttempt attempt) {
        return userAnswerRepository.countByQuizAttempt(attempt);
    }

    public void deleteAnswer(Long id) {
        userAnswerRepository.deleteById(id);
    }
}
