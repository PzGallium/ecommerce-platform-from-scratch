package com.qiuzhitech.onlineshopping_09.controller;

import com.qiuzhitech.onlineshopping_09.config.MyUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private Dependency dependency;

    @Resource(name = "Lyon")
    private MyUser userLyon;

    public int sumPlug2(int a, int b) {
        return dependency.sum(a, b) + 2;
    }

    public HelloController(Dependency  dependency) {
        this.dependency = dependency;
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Hello Post World!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Get World!" + userLyon;
    }

    @RequestMapping("/echo/{abc}")
    public String echo(@PathVariable String abc) {
        return "Hello From: " + abc;
    }
}
