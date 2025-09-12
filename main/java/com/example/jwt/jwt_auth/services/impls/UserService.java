package com.example.jwt.jwt_auth.services.impls;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.entities.User;
import com.example.jwt.jwt_auth.exceptions.BaseException;
import com.example.jwt.jwt_auth.exceptions.ErrorDetail;
import com.example.jwt.jwt_auth.exceptions.MessageType;
import com.example.jwt.jwt_auth.mappers.UserMapper;
import com.example.jwt.jwt_auth.repositories.IUserRepository;
import com.example.jwt.jwt_auth.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    // Constructor Injection
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<DtoUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public DtoUser findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new BaseException(
                        new ErrorDetail(MessageType.NO_RECORD_EXIST, id.toString())
                ));

        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new BaseException(
                        new ErrorDetail(MessageType.NO_RECORD_EXIST, id.toString())
                ));

        userRepository.deleteById(id);
    }

    @Override
    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new BaseException(
                        new ErrorDetail(MessageType.NO_RECORD_EXIST, id.toString())
                ));

        userMapper.updateEntityFromIU(dtoUserIU, user);

        if (dtoUserIU.getPassword() != null && !dtoUserIU.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dtoUserIU.getPassword()));
        }

        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public DtoUser addUser(DtoUserIU newUser) {
        // Username kontrolü
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new BaseException(
                    new ErrorDetail(MessageType.DUPLICATE_USERNAME, newUser.getUsername())
            );
        }

        // Email kontrolü
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new BaseException(
                    new ErrorDetail(MessageType.DUPLICATE_EMAIL, newUser.getEmail())
            );
        }

        User user = userMapper.toEntity(newUser);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }
}
