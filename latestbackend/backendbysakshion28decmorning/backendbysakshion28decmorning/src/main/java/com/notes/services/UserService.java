package com.notes.services;

import com.notes.dto.ApiResponse;
import com.notes.dto.AuthRequest;
import com.notes.dto.AuthResponse;
import com.notes.dto.UserRegDTO;

public interface UserService{

	
	AuthResponse authenticate(AuthRequest dto);

	ApiResponse userRegistration(UserRegDTO regDTO);
	
}
