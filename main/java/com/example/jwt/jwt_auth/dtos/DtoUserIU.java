package com.example.jwt.jwt_auth.dtos;

import com.example.jwt.jwt_auth.entities.Projects;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// For update and delete requests
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserIU {

    private Long id;

    @NotEmpty(message = "Username cannot be empty.")
    @NotNull(message = "Username cannot be null.")
    @Size(min = 3, max = 10)
    private String username;

    @NotEmpty(message = "Password cannot be empty.")
    @NotNull(message = "Password cannot be null.")
    @Size(min = 8, max = 30, message = "Password size must be 8 between 30.")
    private String password;

    @Email(message = "Enter valid email address.")
    @NotEmpty(message = "Email cannot be empty.")
    @NotNull(message = "Email cannot be null.")
    private String email;

    private List<Projects> projects;
}
