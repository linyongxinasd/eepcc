package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.entity.StuClass;
import com.lin.mapper.StuClassMapper;
import com.lin.service.StuClassService;
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
@RequestMapping("/stuClass")
public class StuClassController {

    @Resource
    public StuClassService stuClassService;

    @Resource
    public StuClassMapper stuClassMapper;


    @GetMapping("/getStuClass")
    public Response getStuClass(){
        Response response = new Response();

        List<StuClass> list = stuClassService.getStuClassList();
        response.setData(list);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }

}

