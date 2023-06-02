package com.riarurainey.socialmedia.api.security.auth;

import com.riarurainey.socialmedia.api.security.constraint.ValidPassword;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    @Size(min = 2, max = 40,
            message = "Username must be between 2 and 40 characters in size" )
    private String username;

    @ValidPassword
    private String password;

}
