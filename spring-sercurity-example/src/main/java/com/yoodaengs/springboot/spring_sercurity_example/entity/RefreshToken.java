package com.yoodaengs.springboot.spring_sercurity_example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", updatable = false)
	private Long id;
	
	@Column(name="user_id", nullable = false, unique = true)
	private Long userId;
	
	@Column(name="refresh_token", nullable = false)
	private String refreshToken;
	
	public RefreshToken(Long userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
	
	public RefreshToken update(String newRefreshToken) {
		this.refreshToken = newRefreshToken;
		return this;
	}

}
