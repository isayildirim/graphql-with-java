package com.example.graphql.dto;

import com.example.graphql.model.Role;
import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String userName;

    private String email;

    private Role role;

}
