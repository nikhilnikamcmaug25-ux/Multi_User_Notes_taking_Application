package com.notes.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notes.custom_exception.ApiException;
import com.notes.custom_exception.AuthenticationException;
import com.notes.dto.ApiResponse;
import com.notes.dto.AuthRequest;
import com.notes.dto.AuthResponse;
import com.notes.dto.UserRegDTO;
import com.notes.entities.User;
import com.notes.entities.UserRole;
import com.notes.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

 
    @Override
    public AuthResponse authenticate(AuthRequest dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new AuthenticationException("Invalid Email or password !!"));

        // PASSWORD CHECK USING BCRYPT
        boolean passwordMatches = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Invalid Email or password !!");
        }

        // Convert User -> AuthResponse
        AuthResponse resp = modelMapper.map(user, AuthResponse.class);
        resp.setMessage("Login Successful!!");
        return resp;
    }

   
    @Override
    public ApiResponse userRegistration(UserRegDTO regDTO) {

        // Check duplicate email
        if (userRepository.existsByEmail(regDTO.getEmail()))
            throw new ApiException("Email already exists!");

        // Convert DTO to Entity
        User user = modelMapper.map(regDTO, User.class);

  
        if (regDTO.getRole() != null && !regDTO.getRole().isBlank()) {
            user.setRole(UserRole.valueOf(regDTO.getRole().toUpperCase())); 
        } else {
        
            user.setRole(UserRole.USER);
        }

       
        user.setPassword(passwordEncoder.encode(regDTO.getPassword()));

      
        User saved = userRepository.save(user);

        return new ApiResponse("User registered with id " + saved.getId(), "Success");
    }
}
