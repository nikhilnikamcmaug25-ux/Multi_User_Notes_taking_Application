package com.notes.services;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.notes.custom_exception.AuthenticationException;
import com.notes.dto.AuthRequest;
import com.notes.dto.AuthResponse;
import com.notes.entites.User;
import com.notes.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
	public final UserRepository userRepository;

    
	
	@Override
	public AuthResponse authenticate(AuthRequest dto) {
		//1.validate for user with email and password
		User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElseThrow(()->new AuthenticationException("Invalid Email or password !!"));
		//2.->auth success
		//Create  resp dto - def ctor, call entity getters and setters
		/*
		 * API of ModelMapper
		 * public D map(Source src,Class<D> destination
		 */
		AuthResponse respDTo =modelMapper.map(user, AuthResponse.class);
		respDTo.setMessage("Login Successful!!");
		return respDTo;
	}

}
