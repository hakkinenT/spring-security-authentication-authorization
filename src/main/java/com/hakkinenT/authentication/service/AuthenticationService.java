package com.hakkinenT.authentication.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hakkinenT.authentication.domain.user.Role;
import com.hakkinenT.authentication.domain.user.User;
import com.hakkinenT.authentication.dto.AuthenticationRequestDTO;
import com.hakkinenT.authentication.dto.AuthenticationResponseDTO;
import com.hakkinenT.authentication.dto.UserDTO;
import com.hakkinenT.authentication.dto.UserRegisterDTO;
import com.hakkinenT.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(UserRegisterDTO dto){
        var user = User.builder()
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .email(dto.getEmail())
        .password(passwordEncoder.encode(dto.getPassword()))
        .role(dto.getRole())
        .build();

        User userSaved = repository.save(user);
        
        var jwtToken = jwtService.generateToken(user);
        
        var userDTO = toDTO(userSaved);
        return AuthenticationResponseDTO.builder().user(userDTO).token(jwtToken).build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        var user = repository.findByEmail(dto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var userDTO = toDTO(user);
        return AuthenticationResponseDTO.builder().user(userDTO).token(jwtToken).build();
    }

    private UserDTO toDTO(User user){
        boolean isAdmin = user.getRole() == Role.ADMIN ? true : false;
        return new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), isAdmin);
    } 
    

}

