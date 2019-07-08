package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getAllUser() {
        return userDao.selectByExample(new UserExample());
    }

    @Override
    public User register(User user) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        userExample.or().andNameEqualTo(user.getName());
        if(userDao.countByExample(userExample)!=0){
            return null;
        }
        userDao.insertSelective(user);
        return user;
    }

    @Override
    public User login(String name, String password) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> list=userDao.selectByExample(userExample);
        if(list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public User getById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
