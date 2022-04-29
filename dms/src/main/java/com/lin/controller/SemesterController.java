package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.entity.Semester;
import com.lin.service.SemesterService;
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
@RequestMapping("/semester")
public class SemesterController {

    @Resource
    public SemesterService semesterService;

    @GetMapping("/getSemester")
    public Response getSemester(){
        Response response = new Response();

        List<Semester> list = semesterService.list();

        response.setData(list);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;
    }



}

