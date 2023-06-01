package com.riarurainey.socialmedia.api.security.auth.controller;

import com.riarurainey.socialmedia.api.security.auth.dto.AuthRequestDto;
import com.riarurainey.socialmedia.api.security.auth.dto.AuthResponseDto;
import com.riarurainey.socialmedia.api.security.auth.dto.RegisterRequestDto;
import com.riarurainey.socialmedia.api.security.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody  @Valid AuthRequestDto request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
