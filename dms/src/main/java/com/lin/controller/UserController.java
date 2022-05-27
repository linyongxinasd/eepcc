package com.lin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.param.PasswordParam;
import com.lin.domain.param.UserParam;
import com.lin.domain.vo.UserListVo;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.UserRoleService;
import com.lin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @Resource
    public UserMapper userMapper;
    @PostMapping("/getUserInfo")
    public Response getUserInfo(Principal principal){

        Response response = new Response();

        User user = userMapper.loadUserByUsername(principal.getName());

        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;


    }

    @PostMapping("/updatePassword")
    public Response updatePassword(@RequestBody PasswordParam param, Principal principal){
        Response response = new Response();
        boolean update = userService.updatePassword(param,principal);

        if (update){
            response.setResponseBySuccessMsg("更新密码成功！");
        } else {
            response.setResponseByErrorMsg("更新密码失败！");
        }
        return response;
    }

    @GetMapping("/getUserList")
    public Response getUserList(){
        Response response = new Response();

        List<User> userList = userService.getList();
        response.setData(userList);
        return response;
    }

    @PostMapping("/save")
    public Response add(@RequestBody UserParam param){
        Response response = new Response();

        User user = userMapper.loadUserByUsername(param.getUsername());

        if (null != user){
            response.setResponseByErrorMsg("用户已存在！");
            return response;
        }

        Boolean save = userService.saveUser(param);


        if (save){
            response.setResponseBySuccessMsg("添加成功");
        } else{
           response.setResponseByErrorMsg("添加失败");
        }
        return response;
    }

    @GetMapping("/getUserByName")
    public Response getUserByName(@RequestParam String name){
        Response response = new Response();
        List<User> userList = userService.getUserByName(name);
        response.setData(userList);
        return response;
    }

    @Resource
    public UserRoleService userRoleService;

    @PostMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        Response response = new Response();

        Boolean re = userService.delete(ids);

        if (re){
            response.setResponseBySuccessMsg("删除成功！");
        } else{
            response.setResponseByErrorMsg("删除失败！");
        }

        return response;

    }

    @PostMapping("/update")
    public Response update(@RequestBody UserParam param){
        Response response = new Response();

        Boolean up = userService.updateUserRole(param);

        if (up){
            response.setResponseBySuccessMsg("更新成功！");
        } else {
            response.setResponseByErrorMsg("更新失败！");
        }


        return response;

    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable Long id){

        Response response = new Response();
        User user = userService.getUserById(id);
        response.setData(user);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;

    }

    @PostMapping("/resetPassword/{id}")
    public Response resetPassword(@PathVariable Long id){

        Response response = new Response();

        Boolean reset = userService.resetPassword(id);

        if (reset){
            response.setResponseBySuccessMsg("重置密码成功!");
        } else {
            response.setResponseByErrorMsg("重置密码失败!");
        }


        return response;

    }




}
