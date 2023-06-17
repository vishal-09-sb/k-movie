package com.moviebookingAuthTest.authorizationService.controller;


import com.moviebookingAuth.authorizationService.controller.UserController;
import com.moviebookingAuth.authorizationService.model.User;
import com.moviebookingAuth.authorizationService.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerNewUser_success() {
        User user = new User();
        user.setUserName("test");
        when(userService.registerNewUser(any(User.class))).thenReturn(HttpStatus.OK);

        HttpStatus status = userController.registerNewUser(user);

        verify(userService, times(1)).registerNewUser(user);
        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void registerNewUser_failure() {
        User user = new User();
        user.setUserName("test");
        when(userService.registerNewUser(any(User.class))).thenReturn(HttpStatus.BAD_REQUEST);

        HttpStatus status = userController.registerNewUser(user);

        verify(userService, times(1)).registerNewUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, status);
    }

}


