package com.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.ApiResponse;
import com.notes.dto.AuthRequest;
import com.notes.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController//controller+resp body
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	//contr based D.I
 private final UserService userService;
	
 @PostMapping("/signin")
 public ResponseEntity<?> userAuthentication(@RequestBody AuthRequest dto){
	 System.out.println("in sign in "+dto);
	 try {
		//invoke service layer method
		return ResponseEntity.ok(userService.authenticate(dto));
	} catch (RuntimeException e) {
		System.out.println("err"+e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ApiResponse(e.getMessage(),"Failed"));
	}
 }
}
