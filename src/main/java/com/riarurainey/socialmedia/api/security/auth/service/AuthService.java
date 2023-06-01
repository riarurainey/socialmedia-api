package com.riarurainey.socialmedia.api.security.auth.service;


import com.riarurainey.socialmedia.api.exception.UserAlreadyExistException;
import com.riarurainey.socialmedia.api.security.auth.dto.AuthRequestDto;
import com.riarurainey.socialmedia.api.security.auth.dto.AuthResponseDto;
import com.riarurainey.socialmedia.api.security.auth.dto.RegisterRequestDto;
import com.riarurainey.socialmedia.api.user.Role;
import com.riarurainey.socialmedia.api.user.User;
import com.riarurainey.socialmedia.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;


    public AuthResponseDto register(RegisterRequestDto request) {

        var username = request.getUsername();
        var password = request.getPassword();

        validationUserData(username, password);
        var user = User.builder()
                .username(username)
                .email(request.getEmail())
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder().jwtToken(jwtToken).build();


    }

    public AuthResponseDto authenticate(AuthRequestDto request) {

        var username = request.getUsername();
        var password = request.getPassword();
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = userService.findByUsername(username);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder().jwtToken(jwtToken).build();
    }

    private void validationUserData(String username, String email) {
        if (userService.findByUsernameForRegistration(username)) {
            throw new UserAlreadyExistException(username);
        }

        if (userService.findByEmailForRegistration(email)) {
            throw new UserAlreadyExistException(email);
        }

    }
}
