package com.quizapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "leaderboard",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "quiz_id"})
        }
)
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "rank_position")
    private Integer rank;

    @Column(name = "achieved_at", nullable = false, updatable = false)
    private LocalDateTime achievedAt;

    // Default constructor
    public Leaderboard() {}

    // Constructor with parameters (without setting achievedAt directly)
    public Leaderboard(User user, Quiz quiz, Integer score, Integer rank) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.rank = rank;
    }

    // Automatically set timestamp before persisting
    @PrePersist
    protected void onCreate() {
        this.achievedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getRank() { return rank; }
    public void setRank(Integer rank) { this.rank = rank; }

    public LocalDateTime getAchievedAt() { return achievedAt; }
    public void setAchievedAt(LocalDateTime achievedAt) { this.achievedAt = achievedAt; }
}
