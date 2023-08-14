package com.example.graphql.controller;

import com.example.graphql.dto.UserRequest;
import com.example.graphql.model.UserEntity;
import com.example.graphql.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @QueryMapping
    List<UserEntity> getAllUsers() {

        return userService.getAllUsers();
    }

    @QueryMapping
    UserEntity getUserById(@Argument Long id) {

        return userService.getUserById(id);
    }

    @MutationMapping
    UserEntity createUser(@Argument UserRequest userRequest) {

        return userService.createUser(userRequest);
    }

    @MutationMapping
    UserEntity updateUser(@Argument UserRequest userRequest) {

        return userService.updateUser(userRequest);
    }

    @MutationMapping
    Boolean deleteUser(@Argument Long id) {

        return userService.deleteUser(id);
    }

}
