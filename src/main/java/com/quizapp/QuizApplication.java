package com.quizapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Check database connection
		try (Connection connection = dataSource.getConnection()) {
			if (connection != null && !connection.isClosed()) {
				System.out.println("✅ QuizApp connected to the database successfully!");
			} else {
				System.out.println("❌ Failed to connect to the database.");
			}
		} catch (Exception e) {
			System.out.println("❌ Error connecting to the database: " + e.getMessage());
		}
	}
}
