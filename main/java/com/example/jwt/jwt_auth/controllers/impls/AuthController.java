package com.example.jwt.jwt_auth.controllers.impls;

import com.example.jwt.jwt_auth.controllers.IAuthController;
import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthRequest;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;
import com.example.jwt.jwt_auth.dtos.auth.DtoRefreshTokenRequest;
import com.example.jwt.jwt_auth.repositories.IUserRepository;
import com.example.jwt.jwt_auth.services.IAuthService;
import com.example.jwt.jwt_auth.services.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Override
    public DtoAuthRequest register(@Valid @RequestBody DtoAuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Override
    public DtoAuthResponse login(@Valid @RequestBody DtoAuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    @Override
    public DtoAuthResponse refreshToken(DtoRefreshTokenRequest request) {
        return refreshTokenService.refreshToken(request);
    }

}
