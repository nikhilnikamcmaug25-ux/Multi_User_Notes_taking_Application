package com.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dto.ApiResponse;
import com.notes.services.AdminService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
	public class AdminController {

	    private final AdminService adminService;

	    @GetMapping("/users")
	    public ResponseEntity<?> getAllUsers() {
	      
	            return ResponseEntity.ok(adminService.getAllUsers());
	       
	    }

	    @GetMapping("/notes")
	    public ResponseEntity<?> getAllNotes() {
	       
	            return ResponseEntity.ok(adminService.getAllNotes());
	       
	    }

	    @DeleteMapping("/user/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	        try {
	            return ResponseEntity.ok(adminService.deleteUserById(id));
	        } catch (Exception e) {
	            return ResponseEntity
	                    .status(HttpStatus.BAD_REQUEST)
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }

	    @DeleteMapping("/note/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
	      
	            return ResponseEntity.ok(adminService.deleteNoteById(id));
	        
	    }
	}


