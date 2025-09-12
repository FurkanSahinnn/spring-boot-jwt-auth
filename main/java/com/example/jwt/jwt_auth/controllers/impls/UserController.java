package com.example.jwt.jwt_auth.controllers.impls;

import com.example.jwt.jwt_auth.controllers.IUserController;
import com.example.jwt.jwt_auth.dtos.DtoUser;
import com.example.jwt.jwt_auth.dtos.DtoUserIU;
import com.example.jwt.jwt_auth.services.IUserService;
import com.example.jwt.jwt_auth.shared.RestBaseController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/api/user")
public class UserController implements IUserController {
    @Autowired
    private IUserService userService;

    @GetMapping(path = "/list")
    @Override
    public List<DtoUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    @Override
    public DtoUser findUserById(@PathVariable(name = "id") Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public DtoUser updateUser(@PathVariable(name = "id") Long id,
                              @RequestBody @Valid DtoUserIU dtoUserIU) {
        return userService.updateUser(id, dtoUserIU);
    }

    @PostMapping(path = "/add-new-user")
    @Override
    public DtoUser addUser(@RequestBody @Valid DtoUserIU newUser) {
        return userService.addUser(newUser);
    }
}
