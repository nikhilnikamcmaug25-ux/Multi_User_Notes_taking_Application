package com.notes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.ApiResponse;
import com.notes.dto.AuthRequest;
import com.notes.dto.AuthResponse;
import com.notes.entities.User;
import com.notes.repository.UserRepository;
import com.notes.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElse(null);

        if (user == null)
            return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials", "Failed"));

        // CHECK ROLE MATCH
        if (req.getRole() != null && !req.getRole().isBlank()) {
            if (!user.getRole().name().equalsIgnoreCase(req.getRole())) {
                return ResponseEntity.status(401).body(new ApiResponse("Role mismatch", "Failed"));
            }
        }

        // VALIDATE PASSWORD USING BCRYPT
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials", "Failed"));
        }

        // CREATE TOKEN
        String token = jwtUtil.generateToken(user);

        AuthResponse resp = new AuthResponse();
        resp.setId(user.getId());
        resp.setFirstName(user.getFirstName());
        resp.setLastName(user.getLastName());
        resp.setRole(user.getRole());
        resp.setToken(token);
        resp.setMessage("Login successful");

        return ResponseEntity.ok(resp);
    }
}
