package com.example.graphql.controller;

import com.example.graphql.model.Role;
import com.example.graphql.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.ANY )
@AutoConfigureGraphQlTester
class UserControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @BeforeEach
    void setUp() {

        createUser(new UserEntity("isa", "isa@mail.com", Role.ADMIN));
        createUser(new UserEntity("jesus", "jesus@mail.com", Role.USER));
    }

    @Test
    void when_getAllUsers_should_return_userList() {

        // language=graphql
        String query = """
                query {
                  getAllUsers{
                    id
                    userName
                    role
                    createdDate
                    updatedDate
                  }
                }
                """;

        graphQlTester.document(query).execute().path("getAllUsers").entityList(UserEntity.class).hasSize(2);
    }

    @Test
    void when_createUser_should_createNewUserAndReturnUser() {

        String mutation = """
                mutation {
                  createUser(userRequest: {userName: "yildirim", email: "yildirim@gmail.com", role: ADMIN}) {
                    id
                    userName
                    email
                    role
                    createdDate
                    updatedDate
                  }
                }
                """;
        graphQlTester.document(mutation).execute().path("createUser").entity(UserEntity.class).satisfies(x -> {
            assertEquals("yildirim", x.getUserName());
            assertEquals("yildirim@gmail.com", x.getEmail());
        });
    }

    void createUser(UserEntity user) {

        String mutation = """
                mutation {
                  createUser(userRequest: {userName: "%s", email: "%s", role: %s}) {
                    id
                    userName
                    role
                    createdDate
                    updatedDate
                  }
                }
                """.formatted(user.getUserName(), user.getEmail(), user.getRole());

        graphQlTester.document(mutation).execute().path("createUser");
    }

}