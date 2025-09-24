package com.example.jwt.jwt_auth.services;

import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;
import com.example.jwt.jwt_auth.dtos.auth.DtoRefreshTokenRequest;

public interface IRefreshTokenService {
    public DtoAuthResponse refreshToken(DtoRefreshTokenRequest request);
}
