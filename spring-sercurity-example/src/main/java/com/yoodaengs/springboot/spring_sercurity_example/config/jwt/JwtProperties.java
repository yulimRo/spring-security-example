package com.yoodaengs.springboot.spring_sercurity_example.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
	
	private String issuer;
	private String secretKey;

}
