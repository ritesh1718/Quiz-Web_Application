package com.quizapp.config;

import com.quizapp.entity.Category;
import com.quizapp.entity.User;
import com.quizapp.repository.CategoryRepository;
import com.quizapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create default admin user
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Default admin user created: admin/admin123");
        }

        // Create default regular user
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
            System.out.println("✅ Default user created: user/user123");
        }

        // Create default categories
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Science"));
            categoryRepository.save(new Category("Mathematics"));
            categoryRepository.save(new Category("History"));
            categoryRepository.save(new Category("Programming"));
            categoryRepository.save(new Category("General Knowledge"));
            System.out.println("✅ Default categories created");
        }
    }
}