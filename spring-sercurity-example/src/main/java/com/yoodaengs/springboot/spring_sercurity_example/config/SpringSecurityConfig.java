package com.yoodaengs.springboot.spring_sercurity_example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
        http
                .csrf().disable()
                //.cors(cors ->cors.disable())
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .authorizeHttpRequests((requests) -> requests
                        //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/user")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                ).httpBasic(Customizer.withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("ylro")
                .password("1")
                .roles("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("wjchoi")
                .password("2")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,user2);
    }

}
