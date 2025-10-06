package com.quizapp.service;

import com.quizapp.entity.Quiz;
import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.User;
import com.quizapp.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAttemptService {
    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptService(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    // Start a new attempt
    public QuizAttempt startAttempt(User user, Quiz quiz, int totalQuestions) {
        QuizAttempt attempt = new QuizAttempt(user, quiz, totalQuestions);
        return quizAttemptRepository.save(attempt);
    }

    // Mark an attempt as completed
    public QuizAttempt completeAttempt(QuizAttempt attempt, int score) {
        attempt.setScore(score);
        attempt.completeAttempt();
        return quizAttemptRepository.save(attempt);
    }

    public Optional<QuizAttempt> getAttemptById(Long id) {
        return quizAttemptRepository.findById(id);
    }

    public List<QuizAttempt> getAttemptsByUser(User user) {
        return quizAttemptRepository.findByUser(user);
    }

    public List<QuizAttempt> getAttemptsByQuiz(Quiz quiz) {
        return quizAttemptRepository.findByQuiz(quiz);
    }

    public Optional<QuizAttempt> getLatestAttempt(User user, Quiz quiz) {
        return quizAttemptRepository.findTopByUserAndQuizOrderByStartedAtDesc(user, quiz);
    }

    public void deleteAttempt(Long id) {
        quizAttemptRepository.deleteById(id);
    }
}
