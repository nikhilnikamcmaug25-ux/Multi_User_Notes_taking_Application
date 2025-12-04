package com.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.AuthRequest;
import com.notes.dto.UserRegDTO;
import com.notes.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController//controller+resp body
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	//contr based D.I
 private final UserService userService;
	
 @PostMapping("/signin")
 public ResponseEntity<?> userAuthentication(@Valid @RequestBody AuthRequest dto){
	 System.out.println("in sign in "+dto);
	
		//invoke service layer method
		return ResponseEntity.ok(userService.authenticate(dto));
	
 }
 
 
 @PostMapping("/signup")
  public ResponseEntity<?> userRegistration(@Valid @RequestBody  UserRegDTO regDTO){
	 System.out.println("in sign up"+regDTO);
	
		//invoke service method
		 return ResponseEntity.status(HttpStatus.CREATED)
					.body(userService.userRegistration(regDTO));	
	
 }
}
