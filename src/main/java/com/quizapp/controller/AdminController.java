package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Category;
import com.quizapp.service.AnswerOptionService;
import com.quizapp.service.CategoryService;
import com.quizapp.service.QuestionService;
import com.quizapp.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final QuizService quizService;
    private final CategoryService categoryService;
    private final QuestionService questionService;
    private final AnswerOptionService answerOptionService;

    public AdminController(QuizService quizService, CategoryService categoryService,
                           QuestionService questionService, AnswerOptionService answerOptionService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.questionService = questionService;
        this.answerOptionService = answerOptionService;
    }

    // ===== QUIZ CRUD =====

    // Show all quizzes
    @GetMapping("/quizzes")
    public String quizList(Model model) {
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "admin/quiz_list";
    }

    // Show create form
    @GetMapping("/quizzes/new")
    public String quizCreateForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/quiz_create";
    }

    // Create quiz
    @PostMapping("/quiz/create")
    public String createQuiz(@ModelAttribute Quiz quiz, @RequestParam Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        quiz.setCategory(category);
        quizService.createQuiz(quiz);
        return "redirect:/admin/quizzes";
    }

    // ✅ Edit quiz (GET form)
    @GetMapping("/quizzes/edit/{id}")
    public String editQuizForm(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        model.addAttribute("quiz", quiz);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/quiz_edit";
    }

    // ✅ Update quiz (POST)
    @PostMapping("/quizzes/update/{id}")
    public String updateQuiz(@PathVariable Long id, @ModelAttribute Quiz quiz, @RequestParam Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        quiz.setCategory(category);
        quizService.updateQuiz(quiz);

        return "redirect:/admin/quizzes";
    }

    // ✅ Delete quiz
    @GetMapping("/quizzes/delete/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return "redirect:/admin/quizzes";
    }

    // ===== QUESTION CRUD =====

    // Show add question form
    @GetMapping("/quizzes/{quizId}/questions/add")
    public String showAddQuestionForm(@PathVariable Long quizId, Model model) {
        Quiz quiz = quizService.getQuizById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        model.addAttribute("quiz", quiz);
        return "admin/add_question";
    }

    // Add question
    @PostMapping("/quizzes/{quizId}/questions/add")
    public String createQuestion(@PathVariable Long quizId,
                                 @RequestParam String questionText,
                                 @RequestParam List<String> optionTexts,
                                 @RequestParam int correctOptionIndex) {

        questionService.createQuestionWithOptions(quizId, questionText, optionTexts, correctOptionIndex);
        return "redirect:/admin/quizzes/" + quizId + "/questions";
    }

    // Manage quiz questions
    @GetMapping("/quizzes/{quizId}/questions")
    public String viewQuizQuestions(@PathVariable Long quizId, Model model) {
        Quiz quiz = quizService.getQuizById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questions = questionService.getQuestionsByQuizWithOptions(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        return "admin/manage_questions";
    }

    // Delete question
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@RequestParam Long quizId, @PathVariable Long id) {
        answerOptionService.deleteOptionsByQuestion(id);
        questionService.deleteQuestion(id);
        return "redirect:/admin/quizzes/" + quizId + "/questions";
    }
}
