package com.example.graphql.service;

import com.example.graphql.dto.UserRequest;
import com.example.graphql.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    UserEntity createUser(UserRequest request);

    UserEntity updateUser(UserRequest request);

    boolean deleteUser(Long id);

}
