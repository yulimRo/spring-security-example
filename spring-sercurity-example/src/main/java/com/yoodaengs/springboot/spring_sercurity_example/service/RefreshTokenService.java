package com.yoodaengs.springboot.spring_sercurity_example.service;

import org.springframework.stereotype.Service;

import com.yoodaengs.springboot.spring_sercurity_example.entity.RefreshToken;
import com.yoodaengs.springboot.spring_sercurity_example.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
	
    private final RefreshTokenRepository refreshTokenRepository;
    
    public RefreshToken getRefreshToken(String refreshToken) {
    	return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("UnExpected token"));
    }
}
