package com.notes.services;

import com.notes.dto.AuthRequest;
import com.notes.dto.AuthResponse;

public interface UserService{

	AuthResponse authenticate(AuthRequest dto);
	
}
