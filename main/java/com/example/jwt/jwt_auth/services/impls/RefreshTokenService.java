package com.example.jwt.jwt_auth.services.impls;

import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;
import com.example.jwt.jwt_auth.dtos.auth.DtoRefreshTokenRequest;
import com.example.jwt.jwt_auth.entities.RefreshToken;
import com.example.jwt.jwt_auth.repositories.IRefreshTokenRepository;
import com.example.jwt.jwt_auth.services.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefreshTokenService implements IRefreshTokenService {

    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    // Create refresh token.
    @Override
    public DtoAuthResponse refreshToken(DtoRefreshTokenRequest request) {
        Optional<RefreshToken> refreshTokenFromRepository = refreshTokenRepository
                .findByRefreshToken(request.getRefreshToken());

        // Check the refreshToken exists in DB.
        if (refreshTokenFromRepository.isEmpty()) {
            System.out.println("The refresh token is not valid: " + request.getRefreshToken());
        }

        RefreshToken refreshToken = refreshTokenFromRepository.get();

        // Check the refreshToken Expiration.
        if (!jwtService.isTokenExpired(request.getRefreshToken())) {
            System.out.println("The refresh token expired: " + request.getRefreshToken());
        }

        // Create new refresh token using the access token after new access token is generated.
        String newAccessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedNewRefreshToken = refreshTokenRepository.save(
                jwtService.generateRefreshToken(refreshToken.getUser())
        );

        return new DtoAuthResponse(newAccessToken, savedNewRefreshToken.getRefreshToken());
    }
}
