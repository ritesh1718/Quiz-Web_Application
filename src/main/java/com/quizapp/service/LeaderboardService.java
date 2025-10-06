package com.quizapp.service;

import com.quizapp.entity.Leaderboard;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public Leaderboard addOrUpdateEntry(User user, Quiz quiz, int score, int rank) {
        Optional<Leaderboard> existing = leaderboardRepository.findByUserAndQuiz(user, quiz);
        Leaderboard entry;
        if (existing.isPresent()) {
            entry = existing.get();
            entry.setScore(score);
            entry.setRank(rank);
        } else {
            entry = new Leaderboard(user, quiz, score, rank);
        }
        return leaderboardRepository.save(entry);
    }

    public List<Leaderboard> getLeaderboardByQuiz(Quiz quiz) {
        return leaderboardRepository.findByQuizOrderByScoreDesc(quiz);
    }

    public void deleteLeaderboardByQuiz(Quiz quiz) {
        leaderboardRepository.deleteByQuiz(quiz);
    }

    public Optional<Leaderboard> getEntry(User user, Quiz quiz) {
        return leaderboardRepository.findByUserAndQuiz(user, quiz);
    }
}
