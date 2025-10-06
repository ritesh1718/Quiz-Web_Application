package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.entity.QuizAttempt;
import com.quizapp.entity.UserAnswer;
import com.quizapp.service.UserAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-answers")
public class UserAnswerController {

    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @PostMapping
    public UserAnswer submitAnswer(@RequestBody UserAnswer userAnswer) {
        return userAnswerService.submitAnswer(userAnswer);
    }

    @GetMapping("/attempt/{attemptId}")
    public List<UserAnswer> getAnswersByAttempt(@PathVariable Long attemptId) {
        QuizAttempt attempt = new QuizAttempt();
        attempt.setId(attemptId);
        return userAnswerService.getAnswersByAttempt(attempt);
    }

    @GetMapping("/attempt/{attemptId}/question/{questionId}")
    public List<UserAnswer> getAnswersByAttemptAndQuestion(@PathVariable Long attemptId,
                                                           @PathVariable Long questionId) {
        QuizAttempt attempt = new QuizAttempt();
        attempt.setId(attemptId);
        Question question = new Question();
        question.setId(questionId);
        return userAnswerService.getAnswersByAttemptAndQuestion(attempt, question);
    }

    @DeleteMapping("/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        userAnswerService.deleteAnswer(id);
        return "User answer deleted successfully!";
    }
}
