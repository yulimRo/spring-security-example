package com.yoodaengs.springboot.spring_sercurity_example.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.yoodaengs.springboot.spring_sercurity_example.config.jwt.TokenProvider;
import com.yoodaengs.springboot.spring_sercurity_example.dto.UserDTO;
import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import com.yoodaengs.springboot.spring_sercurity_example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
	
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    
    public String createNewAccessToken(String refreshToken) {
    	if(!tokenProvider.validToken(refreshToken)) {
    		throw new IllegalArgumentException("Unexpected token");
    	}
    	
    	Long userId = refreshTokenService.getRefreshToken(refreshToken).getUserId();
    	User user = userService.getUser(userId);
    	
    	return tokenProvider.generateToken(user, Duration.ofDays(2));
    }
}
