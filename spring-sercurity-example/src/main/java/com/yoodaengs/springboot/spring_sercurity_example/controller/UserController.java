package com.yoodaengs.springboot.spring_sercurity_example.controller;

import com.yoodaengs.springboot.spring_sercurity_example.dto.TokenDTO;
import com.yoodaengs.springboot.spring_sercurity_example.dto.UserDTO;
import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import com.yoodaengs.springboot.spring_sercurity_example.service.TokenService;
import com.yoodaengs.springboot.spring_sercurity_example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/user")
    public User createUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
    
    @PostMapping("/api/token")
    public ResponseEntity<TokenDTO> createNewAccessToken(@RequestBody TokenDTO tokenDTO) {
    	String newAccessToken = tokenService.createNewAccessToken(tokenDTO.getRefreshToken());
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDTO(newAccessToken, tokenDTO.getRefreshToken()));
    }
}
