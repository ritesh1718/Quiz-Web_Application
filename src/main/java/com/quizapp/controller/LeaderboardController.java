package com.quizapp.controller;

import com.quizapp.entity.Leaderboard;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.service.LeaderboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @PostMapping
    public Leaderboard addOrUpdateEntry(@RequestParam Long userId,
                                        @RequestParam Long quizId,
                                        @RequestParam int score,
                                        @RequestParam int rank) {
        User user = new User();
        user.setId(userId);
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        return leaderboardService.addOrUpdateEntry(user, quiz, score, rank);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Leaderboard> getLeaderboardByQuiz(@PathVariable Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        return leaderboardService.getLeaderboardByQuiz(quiz);
    }

    @GetMapping("/quiz/{quizId}/user/{userId}")
    public Optional<Leaderboard> getEntry(@PathVariable Long quizId, @PathVariable Long userId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        User user = new User();
        user.setId(userId);
        return leaderboardService.getEntry(user, quiz);
    }
}
