package com.yoodaengs.springboot.spring_sercurity_example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {
		
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                    .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"))
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	//TODO swagger URL 제거
        http
                .csrf().disable()
                .cors().disable() 
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //token을 사용으로 이한 세션 미사용
                .and()
                .authorizeHttpRequests((requests) -> requests
                        //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/user")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin().disable()
                .httpBasic().disable(); //사용자명, 비밀번호 텍스트로 전송하는 것은 보안에 취약하므로 미사용
        
        return http.build();
    }
}
