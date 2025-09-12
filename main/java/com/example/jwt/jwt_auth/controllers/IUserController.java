package com.example.jwt.jwt_auth.controllers;

import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;

import java.util.List;

public interface IUserController {
    public List<DtoUser> getAllUsers();

    public DtoUser findUserById(Long id);

    public void deleteUser(Long id);

    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU);

    public DtoUser addUser(DtoUserIU newUser);
}
