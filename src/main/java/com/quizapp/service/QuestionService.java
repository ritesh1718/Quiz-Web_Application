package com.quizapp.service;

import com.quizapp.entity.AnswerOption;
import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final AnswerOptionService answerOptionService;

    public QuestionService(QuestionRepository questionRepository,
                           QuizRepository quizRepository,
                           AnswerOptionService answerOptionService) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.answerOptionService = answerOptionService;
    }

    @Transactional
    public Question createQuestionWithOptions(Long quizId, String questionText,
                                              List<String> optionTexts, int correctOptionIndex) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = new Question();
        question.setQuestionText(questionText);
        question.setQuiz(quiz);

        Long questionCount = questionRepository.countByQuizId(quizId);
        question.setQuestionOrder(questionCount.intValue() + 1);

        Question savedQuestion = questionRepository.save(question);

        for (int i = 0; i < optionTexts.size(); i++) {
            AnswerOption option = new AnswerOption();
            option.setOptionText(optionTexts.get(i));
            option.setQuestion(savedQuestion);
            option.setOptionOrder(i + 1);
            option.setIsCorrect(i == correctOptionIndex);
            answerOptionService.createOption(option);
        }

        return savedQuestion;
    }

    public List<Question> getQuestionsByQuizWithOptions(Long quizId) {
        return questionRepository.findByQuizIdWithOptions(quizId);
    }

    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public Long countQuestionsByQuiz(Long quizId) {
        return questionRepository.countByQuizId(quizId);
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question updateQuestion(Long id, String questionText,
                                   List<String> optionTexts, int correctOptionIndex) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setQuestionText(questionText);

        answerOptionService.deleteOptionsByQuestion(id);
        for (int i = 0; i < optionTexts.size(); i++) {
            AnswerOption option = new AnswerOption();
            option.setOptionText(optionTexts.get(i));
            option.setQuestion(question);
            option.setOptionOrder(i + 1);
            option.setIsCorrect(i == correctOptionIndex);
            answerOptionService.createOption(option);
        }

        return questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        answerOptionService.deleteOptionsByQuestion(id);
        questionRepository.deleteById(id);
    }
}
