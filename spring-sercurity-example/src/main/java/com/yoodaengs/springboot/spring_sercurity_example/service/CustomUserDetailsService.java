package com.yoodaengs.springboot.spring_sercurity_example.service;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoodaengs.springboot.spring_sercurity_example.entity.User;
import com.yoodaengs.springboot.spring_sercurity_example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new IllegalArgumentException("사용자없음"));
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), "", authorities);
    }
}
