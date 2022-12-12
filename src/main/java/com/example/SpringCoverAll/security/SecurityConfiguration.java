package com.example.SpringCoverAll.security;

import com.example.SpringCoverAll.authentication.AuthProvider;
import com.example.SpringCoverAll.authentication.StudentDetailsService;
import com.example.SpringCoverAll.roles.ApplicationUserRole;
import com.example.SpringCoverAll.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.SpringCoverAll.roles.ApplicationUserRole.ADMIN;
import static com.example.SpringCoverAll.roles.ApplicationUserRole.STUDENT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final StudentDetailsService userDetailsService;
    private final AuthProvider authProvider;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .userDetailsService(userDetailsService)
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/students/sign_up", "/api/v1/students/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .build();
    }


}
