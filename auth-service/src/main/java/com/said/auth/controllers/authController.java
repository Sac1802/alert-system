package com.said.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.said.auth.DTO.AuthResponse;
import com.said.auth.DTO.userLogin;
import com.said.auth.models.users;
import com.said.auth.services.authService;

@RestController
@RequestMapping("/auth")
public class authController {

    private final authService authService;
    
    public authController(authService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(users user){
        AuthResponse tokens = authService.register(user);
        return ResponseEntity.ok(tokens);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(userLogin user){
        AuthResponse tokens = authService.login(user);
        return ResponseEntity.ok(tokens);
    }
}
