package com.said.auth.services;


import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.said.auth.DTO.AuthResponse;
import com.said.auth.DTO.userLogin;
import com.said.auth.models.users;
import com.said.auth.repository.userRepository;
import com.said.auth.security.Auth;

@Service
public class authService {
    
    private final Auth auth;
    private final userRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public authService(Auth auth, userRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.auth = auth;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AuthResponse register(users user){
        boolean existUser = userRepository.existsByEmail(user.getEmail());
        boolean existUserByUsername = userRepository.existsByName(user.getName());
        if(existUser) throw new RuntimeException("Email already exists");
        if(existUserByUsername) throw new RuntimeException("Username already exists");
        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        users usersaved = userRepository.save(user);
        return new AuthResponse(
            auth.createToken(usersaved.getId(), usersaved.getEmail()),
            auth.createRefreshToken(usersaved.getId()),
            3600
        );
    }


    public AuthResponse login(userLogin data){
        Optional<users> user = userRepository.findByEmail(data.getEmail());
        if(user.isEmpty() || !bCryptPasswordEncoder.matches(data.getPassword(), user.get().getPassword())) 
            throw new RuntimeException("Email or password incorrect");
        return new AuthResponse(
            auth.createToken(user.get().getId(), user.get().getEmail()),
            auth.createRefreshToken(user.get().getId()),
            3600
        );
    }
}
