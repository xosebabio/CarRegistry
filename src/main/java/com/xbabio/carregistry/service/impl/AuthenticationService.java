package com.xbabio.carregistry.service.impl;


import com.xbabio.carregistry.controller.dtos.LoginRequest;
import com.xbabio.carregistry.controller.dtos.LoginResponse;
import com.xbabio.carregistry.controller.dtos.SignUpRequest;
import com.xbabio.carregistry.entity.User;
import com.xbabio.carregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse signup(SignUpRequest request) throws BadRequestException {
        var user = User
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_CLIENT")
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().jwt(jwt).build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
        var user = userRepository.findByEmail(request.getUser()).orElseThrow(() -> new IllegalArgumentException("Invalid user or password"));

        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().jwt(jwt).build();
    }

}
