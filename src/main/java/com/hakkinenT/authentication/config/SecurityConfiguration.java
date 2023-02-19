package com.hakkinenT.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        /*http.cors().and().csrf().disable().authorizeHttpRequests()
        .requestMatchers("/api/v1/auth/**")
        .permitAll()
        .requestMatchers("/api/v1/users/**")
        .hasRole("USER")
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);*/
        http.cors().and().csrf().disable().authorizeHttpRequests((auth) -> 
        auth.requestMatchers("/api/v1/users/**")
        .hasRole("USER")
        .requestMatchers("/api/v1/auth/**")
        .permitAll()
        .anyRequest()
        .authenticated());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
