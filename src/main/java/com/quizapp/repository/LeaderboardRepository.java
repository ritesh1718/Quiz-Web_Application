package com.quizapp.repository;

import com.quizapp.entity.Leaderboard;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {

    List<Leaderboard> findByQuizOrderByScoreDesc(Quiz quiz);

    // helpful convenience - top N can be retrieved by passing a Pageable in service (not defined here)
    Optional<Leaderboard> findByUserAndQuiz(User user, Quiz quiz);

    void deleteByQuiz(Quiz quiz);
}
