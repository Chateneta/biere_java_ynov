package com.biere.controllers;

import java.util.List;

import com.biere.entities.Biere;
import com.biere.entities.User;
import com.biere.services.BiereService;
import com.biere.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BiereService biereService;

    @Operation(summary = "Return user from ID")
    @RequestMapping(path="/me", method = RequestMethod.GET)
    public User get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserById(authentication.getName());
    }

    @Operation(summary = "Return all user")
    @RequestMapping(path="/user/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAllUser();
    }

    @Operation(summary = "Delete user from id")
    @RequestMapping(path="/user/{id}", method = RequestMethod.DELETE)
    public void getAll(@PathVariable(value="id")String id){
        userService.deletUserById(id);
    }

    @Operation(summary = "Add User to biere from id")
    @RequestMapping(path="/user/{uId}/{id}", method = RequestMethod.POST)
    public User subUser(@PathVariable(value="id")Integer id,@PathVariable(value="uId")String uId){
        Biere s = biereService.getBiereById(id);
        User u = userService.getUserById(uId);
        u.setBieres(s);
        return userService.createOrUpdate(u);
    }

    @Operation(summary = "Delete User to biere from id")
    @RequestMapping(path="/user/{uId}/{id}", method = RequestMethod.DELETE)
    public User unSubUser(@PathVariable(value="id")Integer id,@PathVariable(value="uId")String uId){
        Biere s = biereService.getBiereById(id);
        User u = userService.getUserById(uId);
        u.lessBieres(s);
        return userService.createOrUpdate(u);
    }

}
