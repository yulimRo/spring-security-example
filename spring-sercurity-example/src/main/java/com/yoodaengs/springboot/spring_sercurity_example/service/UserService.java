package com.yoodaengs.springboot.spring_sercurity_example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoodaengs.springboot.spring_sercurity_example.dto.UserDTO;
import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import com.yoodaengs.springboot.spring_sercurity_example.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(UserDTO userDTO) {
        return userRepository.save(User.builder()
                        .name(userDTO.getName())
                        .email(userDTO.getEmail())
                        .birth(userDTO.getBirth())
                .build());

    }
    
    public User getUser(Long userId) {
    	return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(""));
    }
}
