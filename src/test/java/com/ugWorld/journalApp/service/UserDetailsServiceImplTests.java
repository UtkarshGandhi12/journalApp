package com.ugWorld.journalApp.service;

import com.ugWorld.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void loadUserByUsernameTests(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.ugWorld.journalApp.entity.User) User.builder().username("ugandhi").password("surya123").roles(String.valueOf(new ArrayList<>())).build());
        UserDetails user= userDetailsService.loadUserByUsername("UG");
        Assertions.assertNotNull(user);
    }
}
