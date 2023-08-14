package com.example.graphql.service.impl;

import com.example.graphql.dto.UserRequest;
import com.example.graphql.exception.UserNotFoundException;
import com.example.graphql.model.UserEntity;
import com.example.graphql.repository.UserRepository;
import com.example.graphql.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User Not Found [ id: %s ]", id)));
    }

    @Override
    public UserEntity createUser(UserRequest request) {

        UserEntity userEntity = UserEntity.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserRequest request) {

        UserEntity userEntity = getUserById(request.getId());

        if ( userEntity != null ) {

            userEntity.setUserName(request.getUserName());
            userEntity.setEmail(request.getEmail());
            userEntity.setRole(request.getRole());

            return userRepository.save(userEntity);
        }

        throw new UserNotFoundException(String.format("User Not Found [ id: %s ]", request.getId()));
    }

    @Override
    public boolean deleteUser(Long id) {

        UserEntity userEntity = getUserById(id);
        if ( userEntity == null ) {
            return false;
        }

        userRepository.delete(userEntity);
        return true;
    }

}
