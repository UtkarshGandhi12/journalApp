package com.ugWorld.journalApp.service;

import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class UserServiceTests {

    @Autowired
    public UserRepository userRepository;
    @Test
    public void testFindByUserName(){
        User user=userRepository.findByUserName("ugandhi");
        assertTrue(!user.getJournalEntries().isEmpty());

    }

}
