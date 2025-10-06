package com.quizapp.service;

import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.repository.QuizRepository;
import com.quizapp.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    // Create new quiz with logged-in user as creator
    public Quiz createQuiz(Quiz quiz) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        quiz.setCreatedBy(creator);
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<Quiz> getQuizzesByCategory(Long categoryId) {
        return quizRepository.findByCategoryId(categoryId);
    }

    public List<Quiz> getQuizzesByUser(User user) {
        return quizRepository.findByCreatedBy(user);
    }

    public List<Quiz> getActiveQuizzes() {
        return quizRepository.findByIsActiveTrue();
    }

    public Quiz updateQuiz(Quiz quiz) {
        // Preserve existing createdBy
        Quiz existing = quizRepository.findById(quiz.getId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        quiz.setCreatedBy(existing.getCreatedBy());
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
