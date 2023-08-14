package com.example.graphql.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode( callSuper = true )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "users" )
public class UserEntity extends BaseEntity {

    private String userName;

    private String email;

    @Enumerated( EnumType.STRING )
    private Role role;

}
