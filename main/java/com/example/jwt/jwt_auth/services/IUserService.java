package com.example.jwt.jwt_auth.services;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.entities.User;

import java.util.List;

public interface IUserService {
    public List<DtoUser> getAllUsers();

    public DtoUser findUserById(Long id);

    public User findUserByUsername(String username);

    public void deleteUser(Long id);

    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU);

    public DtoUser addUser(DtoUserIU newStudent);
}
