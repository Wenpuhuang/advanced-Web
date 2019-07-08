package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.enity.User;
import com.example.demo.enity.UserExample;
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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        userExample.or().andUsernameEqualTo(user.getUsername());
        if (userDao.countByExample(userExample) != 0) {       //如果用户名或邮箱已存在，返回null
            return null;
        }
        userDao.insert(user);
        return user;
    }

    @Override
    public User login(String name, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);     //比较数据库中是否存在这个用户名，密码
        List<User> list = userDao.selectByExample(userExample);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
