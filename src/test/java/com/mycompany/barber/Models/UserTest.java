package com.mycompany.barber.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class UserTest {

    @Test
    void getUserId() throws JsonProcessingException {
        User user = new User();
        user.setUserId(1);
        String testResult = new ObjectMapper().writeValueAsString(user);
        assertThat(testResult,testResult.contains("\"userId\":1"));    }

    @Test
    void getUserName() {
    }

    @Test
    void getUserRank() {
    }

    @Test
    void getUserPhoneNumber() {
    }

    @Test
    void getUserProfession() {
    }

    @Test
    void getUserRole() {
    }

    @Test
    void getUserAccessed1() {
    }

    @Test
    void getUserAccessed2() {
    }
}