package com.ugWorld.journalApp.service;

import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    public UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

   public boolean saveNewUser(User user){
     try{
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("User"));
         userRepository.save(user);
         return true;
     } catch (Exception e) {
         log.error("error occured for user {} :",user.getUserName());
     }
     return false;
   }
   public void saveAdmin(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRoles(Arrays.asList("User","Admin"));
            userRepository.save(user);
   }

    public void saveEntry(User user){
        userRepository.save(user);

    }
   public List<User> getAll(){
       return userRepository.findAll();
}
    public void deleteEntry(ObjectId id){
        userRepository.deleteById(id);}

//    public void deleteByUserName(String userName){
//        userRepository.deleteByName(userName);
//        }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
   }
    public User findByUserName(String userName){
      return userRepository.findByUserName(userName);
    }
       }
