package com.apartment.vmoproject.api.service;

import com.apartment.vmoproject.api.model.AuthenticationRequest;
import com.apartment.vmoproject.api.model.AuthenticationResponse;
import com.apartment.vmoproject.api.model.CustomUserDetail;
import com.apartment.vmoproject.api.model.Account;
import com.apartment.vmoproject.api.repository.AccountRepository;
import com.apartment.vmoproject.api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(AuthenticationRequest request) {
        var user = Account.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername() )
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(new CustomUserDetail(user));

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername());
        if(user==null){
            return null;
        }
        var jwtToken = jwtService.generateToken(new CustomUserDetail(user.get()));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
