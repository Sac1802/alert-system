package com.said.auth.mapper;

import com.said.auth.DTO.userDTO;
import com.said.auth.DTO.userResponseDTO;
import com.said.auth.models.users;

public class userMapper {

    public users mapToUser(userDTO userDTO) {
        users user = new users();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public userDTO mapToUserDTO(users user) {
        userDTO userDTO = new userDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public userResponseDTO mapToUserResponseDTO(users user) {
        userResponseDTO userResponseDTO = new userResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
    
}
