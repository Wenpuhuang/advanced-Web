package com.example.demo.controller;

import com.example.demo.enity.User;
import com.example.demo.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "login", produces = "application/json;UTF-8")
    public String login(@RequestParam() String username, @RequestParam() String password) {
        User user = userService.login(username, password);
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            jsonObject.put("errorCode", 1000);
            jsonObject.put("message", "用户名或密码错误");
            return jsonObject.toJSONString();
        }
        jsonObject.put("errorCode", 0);
        jsonObject.put("user", user);
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "register", produces = "application/json;UTF-8")
    public String register(@RequestParam() String username, @RequestParam() String password,
                           @RequestParam() String email, @RequestParam() String phone) {
        JSONObject jsonObject = new JSONObject();
        if (isEmpty(username) || isEmpty(password) || isEmpty(email) || isEmpty(email)) {
            jsonObject.put("errorCode", 1001);
            jsonObject.put("message", "输入信息不能为空");
            return jsonObject.toJSONString();
        }
        if (!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")) {

            jsonObject.put("errorCode", 1002);
            jsonObject.put("message", "邮箱格式不正确");
            return jsonObject.toJSONString();
        }
        User user = new User(username, password, email, phone);
        user = userService.register(user);
        if (user == null) {
            jsonObject.put("errorCode", 1003);
            jsonObject.put("message", "用户名或者邮箱已存在");
            return jsonObject.toJSONString();
        }
        jsonObject.put("erroeCode", 0);
        jsonObject.put("user", user);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "all", produces = "application/json;UTF-8")
    public String getAll() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode", 0);
        jsonObject.put("list", userService.getAllUser());
        return jsonObject.toJSONString();
    }

    private boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }
}
