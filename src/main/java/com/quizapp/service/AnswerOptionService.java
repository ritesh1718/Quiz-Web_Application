package com.quizapp.service;

import com.quizapp.entity.AnswerOption;
import com.quizapp.repository.AnswerOptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;

    public AnswerOptionService(AnswerOptionRepository answerOptionRepository) {
        this.answerOptionRepository = answerOptionRepository;
    }

    public AnswerOption createOption(AnswerOption option) {
        return answerOptionRepository.save(option);
    }

    public List<AnswerOption> getOptionsByQuestion(Long questionId) {
        return answerOptionRepository.findByQuestionIdOrderByOptionOrderAsc(questionId);
    }

    public Optional<AnswerOption> getCorrectOption(Long questionId) {
        List<AnswerOption> correctOptions = answerOptionRepository.findByQuestionIdAndIsCorrectTrue(questionId);
        if (correctOptions != null && !correctOptions.isEmpty()) {
            return Optional.of(correctOptions.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Long countOptions(Long questionId) {
        return (long) answerOptionRepository.findByQuestionIdOrderByOptionOrderAsc(questionId).size();
    }

    public Optional<AnswerOption> getOptionById(Long id) {
        return answerOptionRepository.findById(id);
    }

    public void deleteOption(Long id) {
        answerOptionRepository.deleteById(id);
    }

    public void deleteOptionsByQuestion(Long questionId) {
        List<AnswerOption> options = answerOptionRepository.findByQuestionIdOrderByOptionOrderAsc(questionId);
        answerOptionRepository.deleteAll(options);
    }
}
