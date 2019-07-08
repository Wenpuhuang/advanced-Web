package com.example.demo.controller;

//import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "login" ,produces = "application/json;UTF-8")
    public String login(@RequestParam()String name,@RequestParam() String password){
        User user=userService.login(name, password);
        JSONObject jsonObject=new JSONObject();
        if(user==null){
            jsonObject.put("errorCode",1000);
            jsonObject.put("message","用户名或密码错误");
            return jsonObject.toJSONString();
        }
        jsonObject.put("errorCode",0);
        jsonObject.put("user",user);
        return jsonObject.toJSONString();
    }
    @PostMapping(value = "register",produces = "application/json;UTF-8")
    public String register(@RequestParam()String name,@RequestParam()String password,
                           @RequestParam()String email,@RequestParam()String phone){
        JSONObject jsonObject=new JSONObject();
        if(isEmpty(name)||isEmpty(password)||isEmpty(email)||isEmpty(email)){
            jsonObject.put("errorCode",1001);
            jsonObject.put("message","输入信息不能为空");
            return jsonObject.toJSONString();
        }
        if(!email.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$")){
            jsonObject.put("errorCode",1002);
            jsonObject.put("message","邮箱格式不正确");
            return jsonObject.toJSONString();
        }
        User user=new User(name,password,email,phone);
        user=userService.register(user);
        if(user==null){
            jsonObject.put("errorCode",1003);
            jsonObject.put("message","用户名或者邮箱已存在");
            return jsonObject.toJSONString();
        }
        jsonObject.put("erroeCode",0);
        jsonObject.put("user",user);
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "all",produces = "application/json;UTF-8")
    public String getAll(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("errorCode",0);
        jsonObject.put("list",userService.getAllUser());
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "detail/{id}",produces = "application/json;UTF-8")
    public String getUserById(@PathVariable()String id){
        JSONObject jsonObject=new JSONObject();
        if(id==null||!id.matches("^[0-9]+")){
            jsonObject.put("errorCode",1004);
            jsonObject.put("message","id不为数字");
            return jsonObject.toJSONString();
        }
        User user=userService.getById(parseInt(id));
        if(user==null){
            jsonObject.put("errorCode",1005);
            jsonObject.put("message","该用户不存在");
            return jsonObject.toJSONString();
        }else {
            jsonObject.put("errorCode",0);
            jsonObject.put("user",user);
            return jsonObject.toJSONString();
        }
    }
    private boolean isEmpty(String string){
        return string==null||string.trim().equals("");
    }
}
