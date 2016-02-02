/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gene.game.controllers;

import com.gene.game.models.User;
import com.gene.game.models.UserDao;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gene
 */
@Controller
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String create(String email, String name) {
        User user = null;
        try {
            user = new User(email, name);
            userDao.save(user);
        } catch (Exception e) {
            return "Error creating the user: "+ e.toString();
        }
        
        return "User successfully created! (id = "+ user.getId() +")";
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        Iterable<User> users;
        try {
            users = userDao.findAll();
        } catch (Exception e) {
//            return "Error finding all users: "+ e.toString();
            return null;
        }
        
        return (List<User>) users;
    }
    
    @RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable String id) {
        return userDao.findOne(Long.valueOf(id));
    } 
    
    @RequestMapping(value = "/get-by-email/{email}", method = RequestMethod.GET)
    @ResponseBody
    public String getByEmail(@PathVariable String email) {
        String userId;
        try {
            User user = userDao.findByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception e) {
            return "User not found";
        }
        
        return "The user id is: "+ userId;
    }
    
}