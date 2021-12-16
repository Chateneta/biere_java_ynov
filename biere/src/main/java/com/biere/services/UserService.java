package com.biere.services;

import java.util.List;

import com.biere.entities.User;
import com.biere.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createOrUpdate(User user) { 
        return userRepository.save(user); 
    } 
       
    public User getUserById(String username) { 
        return userRepository.findById(username).orElse(null); 
    }

    public List<User> getAllUser() { 
        return userRepository.findAll(); 
    }

    public void deletUserById(String username){
        userRepository.deleteById(username);;
    }
}
