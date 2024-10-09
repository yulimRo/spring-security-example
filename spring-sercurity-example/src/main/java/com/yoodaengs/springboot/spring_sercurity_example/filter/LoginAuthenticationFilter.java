//package com.yoodaengs.springboot.spring_sercurity_example.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
//	
//	private final AuthenticationManager authenticationManager;
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		
//		return super.attemptAuthentication(request, response);
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		super.successfulAuthentication(request, response, chain, authResult);
//	}
//
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException failed) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		super.unsuccessfulAuthentication(request, response, failed);
//	}
//	
//	
//
//}
