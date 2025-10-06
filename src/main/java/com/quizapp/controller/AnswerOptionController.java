package com.quizapp.controller;

import com.quizapp.entity.AnswerOption;
import com.quizapp.service.AnswerOptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    public AnswerOptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }

    // Create a new answer option
    @PostMapping
    public AnswerOption createOption(@RequestBody AnswerOption option) {
        return answerOptionService.createOption(option);
    }

    // Get all options for a specific question
    @GetMapping("/question/{questionId}")
    public List<AnswerOption> getOptionsByQuestion(@PathVariable Long questionId) {
        return answerOptionService.getOptionsByQuestion(questionId);
    }

    // Get the correct option for a specific question
    @GetMapping("/question/{questionId}/correct")
    public AnswerOption getCorrectOption(@PathVariable Long questionId) {
        Optional<AnswerOption> correctOption = answerOptionService.getCorrectOption(questionId);
        return correctOption.orElse(null); // return null if not found
    }

    // Count options for a question
    @GetMapping("/question/{questionId}/count")
    public Long countOptions(@PathVariable Long questionId) {
        return answerOptionService.countOptions(questionId);
    }

    // Delete an answer option by id
    @DeleteMapping("/{id}")
    public String deleteOption(@PathVariable Long id) {
        answerOptionService.deleteOption(id);
        return "Answer option deleted successfully!";
    }
}
