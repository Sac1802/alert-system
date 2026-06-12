package com.said.auth.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.said.auth.models.users;
import com.said.auth.repository.userRepository;
import com.said.auth.DTO.userResponseDTO;
import com.said.auth.mapper.userMapper;

@Service
public class userService {
    private final userRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final userMapper userMapper;

    public userService(userRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, userMapper userMapper){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    public List<userResponseDTO> getUsers(){
        List<users> listUsers = userRepository.findAll();
        return listUsers.stream().map(userMapper::mapToUserResponseDTO).collect(Collectors.toList());
    }

    public userResponseDTO getUserByid(Long id){
        users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.mapToUserResponseDTO(user);
    }

    public userResponseDTO updateUser(Long id, users user){
        users userToUpdate = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        users userUpdated = userRepository.save(userToUpdate);
        return userMapper.mapToUserResponseDTO(userUpdated);
    }

    public userResponseDTO updatedPassword(String email, String newPassword){
        Optional<users> userFind = userRepository.findByEmail(email);
        if (userFind.isEmpty()) throw new RuntimeException("user not found");
        users user = userFind.get();
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        users userUpdated = userRepository.save(user);
        return userMapper.mapToUserResponseDTO(userUpdated);
    }

    public String deteleUser(Long id){
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
