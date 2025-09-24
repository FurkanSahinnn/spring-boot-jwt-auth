package com.example.jwt.jwt_auth.dtos.auth;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DtoAuthResponse {
    /**
     * Return created new access and refresh tokens.
     */
    private String token;
    private String refreshToken;
}
