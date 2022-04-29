package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.entity.Type;
import com.lin.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyx
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    public TypeService typeService;


    @GetMapping("/getType")
    public Response getType(){

        Response response = new Response();

        List<Type> type = typeService.list();

        response.setData(type);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;
    }



}

