package com.example.jwt.jwt_auth.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoRegisterRequest {

    @NotEmpty(message = "Username cannot be empty.")
    @Size(min = 3, max = 10)
    private String username;

    @Email(message = "Enter valid email address.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 8, max = 30, message = "Password size must be 8 between 30.")
    private String password;
}
