package com.qiuzhitech.onlineshopping_09.controller;

import com.qiuzhitech.onlineshopping_09.config.MyUser;
import com.qiuzhitech.onlineshopping_09.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private Map<Integer, MyUser> myUser = new HashMap<>();

    @Resource(name = "Lyon")
    private MyUser defaultUser;

    @Resource
    com.qiuzhitech.onlineshopping_09.service.JwtService jwtService;

    @PostMapping("/users")
    public String addUser(@RequestParam int id,
                          @RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String email,
                          Map<String, Object> map) {
        MyUser newUser = new MyUser(id, name, age, email);
        String token = jwtService.encryptUser(newUser);
        String decrptedUserId = jwtService.DecryptUserName(token);
        myUser.put(id, newUser);
        map.put("user", newUser);
        map.put("jwtToken", token);
        map.put("jwtUserId", decrptedUserId);
        return "user_detail";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable int id,
                          Map<String, Object> map) {
        MyUser user = myUser.getOrDefault(id, defaultUser);
        map.put("user", user);
        return "user_detail";
    }
}
