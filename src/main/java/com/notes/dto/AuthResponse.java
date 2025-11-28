package com.notes.dto;

import com.notes.entites.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
private Long id;
private String firstName;
private String lastName;
private UserRole role;
private String message;
}
