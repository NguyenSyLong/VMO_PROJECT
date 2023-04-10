package com.apartment.vmoproject.api.controller;


import com.apartment.vmoproject.api.model.AuthenticationResponse;
import com.apartment.vmoproject.api.model.AuthenticationRequest;
import com.apartment.vmoproject.api.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthenticationService service;



    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody AuthenticationRequest request){
        AuthenticationResponse authenticationResponse = service.register(request);
        return ResponseEntity.ok().body(authenticationResponse);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
