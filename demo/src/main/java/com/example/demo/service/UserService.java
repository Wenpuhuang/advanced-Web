package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User register(User user);
    User login(String name,String password);
    User getById(Integer id);
}
