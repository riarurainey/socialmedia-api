package com.riarurainey.socialmedia.api.security.auth;

import com.riarurainey.socialmedia.api.security.constraint.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @Size(min = 2, max = 40,
            message = "Username must be between 2 and 40 characters in size" )
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @ValidPassword
    private String password;
}

