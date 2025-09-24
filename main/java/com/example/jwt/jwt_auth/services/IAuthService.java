package com.example.jwt.jwt_auth.services;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthRequest;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;

public interface IAuthService {
    public DtoAuthRequest register(DtoAuthRequest request);

    public DtoAuthResponse login(DtoAuthRequest request);
}
