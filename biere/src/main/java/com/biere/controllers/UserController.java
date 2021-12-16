package com.biere.controllers;

import java.util.List;

import com.biere.entities.Biere;
import com.biere.entities.User;
import com.biere.services.BiereService;
import com.biere.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BiereService biereService;


    public void store(User u, User info){
        u.setUsername(info.getUsername());
        u.setFirstname(info.getFirstname());
        u.setLastname(info.getLastname());
        u.setPhone(info.getPhone());
        userService.createOrUpdate(u);
    }

    @Operation(summary = "Return user from ID")
    @RequestMapping(path="/user", method = RequestMethod.GET)
    public User get(@RequestParam(value = "id") String username){
        return userService.getUserById(username);
    }

    @Operation(summary = "Edit User")
    @RequestMapping(path="/user", method = RequestMethod.PUT)
    public User edit(@RequestBody User user){
        User u = userService.getUserById(user.getUsername());
        if(u == null){
            u = new User();
        }
        store(u, user);
        return userService.getUserById(user.getUsername());
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
