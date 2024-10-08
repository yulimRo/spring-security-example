package com.yoodaengs.springboot.spring_sercurity_example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDTO {
	
	private String accessToken;
	private String refreshToken;

}
