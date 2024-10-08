package com.yoodaengs.springboot.spring_sercurity_example.config.jwt;




import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoodaengs.springboot.spring_sercurity_example.dto.TokenDTO;
import com.yoodaengs.springboot.spring_sercurity_example.entity.RefreshToken;
import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import com.yoodaengs.springboot.spring_sercurity_example.repository.RefreshTokenRepository;
import com.yoodaengs.springboot.spring_sercurity_example.repository.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {
	
	@Autowired protected MockMvc mockMvc;
	
	@Autowired protected ObjectMapper objectMapper;
	
	@Autowired private WebApplicationContext context;
	
	@Autowired JwtProperties jwtProperties;
	
	@Autowired UserRepository userRepository;
	
	@Autowired RefreshTokenRepository refreshTokenRepository;
	
	@BeforeEach
	public void mockMvcSetup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.build();
		userRepository.deleteAll();
	}
	
	@DisplayName("createNewAccessToken : 새로운 액세스 토큰을 발급한다.")
	@Test
	public void createNewAccesToken() throws Exception {
		final String url = "/api/token";
		
		User user = userRepository.save(User.builder()
				.email("user@email.com")
				.name("노유림")
				.birth("19970217")
				.password("12345")
				.build());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",user.getId());
		
		
		String refreshToken = JwtFactory.builder()
				.claims(map)
				.build()
				.createToken(jwtProperties);
		
		refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));
		
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setRefreshToken(refreshToken);
		
		final String requestBody = objectMapper.writeValueAsString(tokenDTO);
		
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON_VALUE));
		
		resultActions
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(jsonPath("$.accessToken").isNotEmpty());
		
	}
}
