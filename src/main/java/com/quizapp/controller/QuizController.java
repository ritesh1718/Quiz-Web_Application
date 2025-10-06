package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.service.QuestionService;
import com.quizapp.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    // Show available quizzes for users
    @GetMapping("/list")
    public String showQuizList(Model model) {
        model.addAttribute("quizzes", quizService.getActiveQuizzes());
        return "user/quiz_list";
    }

    // Start Quiz
    @GetMapping("/{quizId}/start")
    public String startQuiz(@PathVariable Long quizId, Model model) {
        Quiz quiz = quizService.getQuizById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questions = questionService.getQuestionsByQuizWithOptions(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);

        return "user/quiz_take";
    }
}
