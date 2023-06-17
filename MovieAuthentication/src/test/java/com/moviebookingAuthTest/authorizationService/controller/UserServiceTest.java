package com.moviebookingAuthTest.authorizationService.controller;


import com.moviebookingAuth.authorizationService.model.Role;
import com.moviebookingAuth.authorizationService.model.User;
import com.moviebookingAuth.authorizationService.repository.RoleDao;
import com.moviebookingAuth.authorizationService.repository.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.moviebookingAuth.authorizationService.service.UserService;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Mock
    RoleDao roleDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerNewUser_success() {
        User user = new User();
        user.setUserPassword("password");

        Role role = new Role();
        role.setRoleName("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);

        when(roleDao.findById("User")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userDao.save(any(User.class))).thenReturn(user);

        HttpStatus status = userService.registerNewUser(user);

        verify(userDao, times(1)).save(user);
        assertEquals(HttpStatus.OK, status);
    }


}
