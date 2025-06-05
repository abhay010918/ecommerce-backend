package com.ecommerce.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF using the new API
                .cors(AbstractHttpConfigurer::disable)  // Disable CORS using the new API
                .addFilterBefore(jwtFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/login"
                                ,"/api/users/login-otp"
                                ,"/api/users/register"
                                ,"/api/auth/**"
                                ,"/actuator/**"
                        ).permitAll()

//                        // ADMIN-only endpoints
//                        .requestMatchers(""
//
//                        ).hasRole("ADMIN")
//
//                        // ADMIN + COSTUMER endpoints
//                        .requestMatchers(""
//                        )
//                        .hasAnyRole("ADMIN", "COSTUMER")
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }
}
