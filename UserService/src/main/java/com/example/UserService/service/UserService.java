package com.example.UserService.service;

import com.example.UserService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);


}
