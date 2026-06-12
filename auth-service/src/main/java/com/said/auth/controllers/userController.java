package com.said.auth.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.said.auth.DTO.userResponseDTO;
import com.said.auth.models.users;
import com.said.auth.services.userService;

@RestController
@RequestMapping("/api")
public class userController {

    private final userService userService;
    
    public userController(userService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public List<userResponseDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/id")
    public userResponseDTO getUserById(Long id) {
        return userService.getUserByid(id);
    }

    @PutMapping("/users")
    public userResponseDTO updateUser(Long id, users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users")
    public String deleteUser(Long id) {
        userService.deteleUser(id);
        return "User deleted successfully";
    }

}
