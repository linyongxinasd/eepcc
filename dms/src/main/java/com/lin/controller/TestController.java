package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.vo.UserVo;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Resource
    UserMapper userMapper;

    @Resource
    public UserService userService;

    @GetMapping("/getTeachers/{id}")
    public Response getTeachers(@PathVariable("id") Long id){
        Response response = new Response();

        List<UserVo> user = userService.getUserList(id);

        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;

    }

   /* @GetMapping("/getEvaluator")
    public Response getEvaluator(){
        Response response = new Response();

        List<UserVo> user = userService.getUserList();

        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;

    }

    @GetMapping("/getDirector")
    public Response getDirector(){
        Response response = new Response();

        List<UserVo> user = userService.getUserList();

        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;

    }*/


}
