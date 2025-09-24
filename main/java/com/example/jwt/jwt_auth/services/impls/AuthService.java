package com.example.jwt.jwt_auth.services.impls;

import com.example.jwt.jwt_auth.controllers.IAuthController;
import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthRequest;
import com.example.jwt.jwt_auth.dtos.auth.DtoAuthResponse;
import com.example.jwt.jwt_auth.entities.RefreshToken;
import com.example.jwt.jwt_auth.entities.User;
import com.example.jwt.jwt_auth.mappers.UserMapper;
import com.example.jwt.jwt_auth.repositories.IRefreshTokenRepository;
import com.example.jwt.jwt_auth.repositories.IUserRepository;
import com.example.jwt.jwt_auth.services.IAuthService;
import com.example.jwt.jwt_auth.services.IRefreshTokenService;
import com.example.jwt.jwt_auth.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    @Override
    public DtoAuthRequest register(DtoAuthRequest request) {
        DtoUserIU userIU = userMapper.authRequestToUserIU(request);

        // Validate and save user to db.
        DtoUser createdUser = userService.addUser(userIU);

        DtoAuthRequest response = new DtoAuthRequest();
        response.setUsername(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        response.setPassword(null); // Not return user password.

        return response;
    }

    @Override
    public DtoAuthResponse login(DtoAuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authenticationManager.authenticate(authToken);

            User user = userService.findUserByUsername(request.getUsername());

            String accessToken = jwtService.generateToken(user);
            RefreshToken refreshToken = jwtService.generateRefreshToken(user);

            refreshTokenRepository.save(refreshToken);
            return new DtoAuthResponse(accessToken, refreshToken.getRefreshToken());

        } catch (Exception e) {
            System.out.println("The Username or password are not valid.");
        }
        return null;
    }
}
