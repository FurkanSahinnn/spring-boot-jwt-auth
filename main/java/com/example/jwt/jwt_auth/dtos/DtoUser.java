package com.example.jwt.jwt_auth.dtos;

import com.example.jwt.jwt_auth.entities.Projects;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// For get requests
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private Long id;

    private String username;

    private String email;

    private List<Projects> projects;
}
