package com.notes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequest {
    private String email;
    private String password;
    private String role; // optional: "USER" or "ADMIN"
}
