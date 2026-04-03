package com.qiuzhitech.onlineshopping_09.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/echo/{abc}")
    public String echo(@PathVariable String abc) {
        return "Hello From: " + abc;
    }
}
