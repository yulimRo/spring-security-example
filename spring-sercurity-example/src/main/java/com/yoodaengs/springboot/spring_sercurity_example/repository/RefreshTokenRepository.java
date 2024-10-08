package com.yoodaengs.springboot.spring_sercurity_example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoodaengs.springboot.spring_sercurity_example.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	Optional<RefreshToken> findByUserId(Long userId);
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
}
