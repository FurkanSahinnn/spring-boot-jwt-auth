package com.example.jwt.jwt_auth.controllers;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthRequest;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;
import com.example.jwt.jwt_auth.dtos.auth.DtoRefreshTokenRequest;

public interface IAuthController {
    public DtoAuthRequest register(DtoAuthRequest request);

    public DtoAuthResponse login(DtoAuthRequest request);

    public DtoAuthResponse refreshToken(DtoRefreshTokenRequest request);
}
