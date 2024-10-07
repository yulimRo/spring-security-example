package com.yoodaengs.springboot.spring_sercurity_example.config.jwt;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.yoodaengs.springboot.spring_sercurity_example.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {
	
	private final JwtProperties jwtProperties;
	
	public String generateToken(User user, Duration expiredAt) {
		Date now = new Date();
		return makeToken(new Date(now.getTime() + expiredAt.toMillis()),user);
	}
	
	//JWT 토큰 생성 메서드
	private String makeToken(Date expiry, User user) {
		Date now = new Date();
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE) //타입
				.setIssuedAt(now) //발급일시
				.setIssuer(jwtProperties.getIssuer()) //발급자
				.setSubject(user.getEmail()) //토큰제목
				.claim("id",user.getId()) //유저ID
				.signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) //HS256 방식 암호화
				.compact();
	}
	//JWT 토큰 유효성 검증 메서드
	public boolean validToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(jwtProperties.getSecretKey())
			.parseClaimsJws(token);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	//토큰 기반으로 인증 정보를 가져오는 메서드
	public Authentication getAuthentication(String token) {
		Claims claims = getClaims(token);
		Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
		
		return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(),
				"", authorities), token, authorities);
	}
	
	public Long getUserId(String token) {
		Claims claims = getClaims(token);
		return claims.get("id", Long.class);
	}
	
	
	private Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(jwtProperties.getSecretKey())
				.parseClaimsJws(token)
				.getBody();
	}

}
