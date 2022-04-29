package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
public class UserController {

    @Resource
    public UserService userService;

    @Resource
    public UserMapper userMapper;
    @RequestMapping("/getUserInfo")
    public Response getUserInfo(Principal principal){

        Response response = new Response();

        User user = userMapper.loadUserByUsername(principal.getName());

        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;


    }





}
