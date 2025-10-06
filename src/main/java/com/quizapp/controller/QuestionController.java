package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<Question> createQuestionWithOptions(
            @PathVariable Long quizId,
            @RequestBody Map<String, Object> requestBody) {

        String questionText = (String) requestBody.get("questionText");
        List<String> optionTexts = (List<String>) requestBody.get("options");
        int correctOptionIndex = Integer.parseInt(requestBody.get("correctOptionIndex").toString());

        Question created = questionService.createQuestionWithOptions(
                quizId, questionText, optionTexts, correctOptionIndex);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        List<Question> questions = questionService.getQuestionsByQuizWithOptions(quizId);
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long id,
            @RequestBody Map<String, Object> requestBody) {

        String questionText = (String) requestBody.get("questionText");
        List<String> optionTexts = (List<String>) requestBody.get("options");
        int correctOptionIndex = Integer.parseInt(requestBody.get("correctOptionIndex").toString());

        Question updated = questionService.updateQuestion(id, questionText, optionTexts, correctOptionIndex);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully!");
    }
}
