package com.notes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.notes.entities.User;
import com.notes.entities.UserRole;
import com.notes.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@example.com";
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPhone("0000000000");
            admin.setRole(UserRole.ADMIN);
            userRepository.save(admin);
        }
    }
}
