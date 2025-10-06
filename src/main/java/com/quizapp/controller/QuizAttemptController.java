package com.quizapp.controller;

import com.quizapp.entity.Quiz;
import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.User;
import com.quizapp.service.QuizAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz-attempts")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping("/start")
    public QuizAttempt startAttempt(@RequestParam Long userId,
                                    @RequestParam Long quizId,
                                    @RequestParam int totalQuestions) {
        User user = new User();
        user.setId(userId);
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        return quizAttemptService.startAttempt(user, quiz, totalQuestions);
    }

    @PostMapping("/complete/{attemptId}")
    public QuizAttempt completeAttempt(@PathVariable Long attemptId,
                                       @RequestParam int score) {
        Optional<QuizAttempt> attempt = quizAttemptService.getAttemptById(attemptId);
        return attempt.map(a -> quizAttemptService.completeAttempt(a, score)).orElse(null);
    }

    @GetMapping("/{id}")
    public Optional<QuizAttempt> getAttemptById(@PathVariable Long id) {
        return quizAttemptService.getAttemptById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAttempt(@PathVariable Long id) {
        quizAttemptService.deleteAttempt(id);
        return "Quiz attempt deleted successfully!";
    }
}
