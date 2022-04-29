package com.lin.controller;


import com.lin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String helloController(){
        return "hello";
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/getPassword")
    public String getPassword(){
        String password = bCryptPasswordEncoder.encode("111111");
        log.info(password);
        return password;
    }



    @Resource
    public UserService userService;

    @GetMapping("/getName")
    public String getName(){

        //return userService.getName();
        return "你好啊";
    }
}
