package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.vo.GradeClassVo;
import com.lin.entity.StuGrade;
import com.lin.service.StuGradeService;
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
@RequestMapping("/stuGrade")
public class StuGradeController {


    @Resource
    public StuGradeService stuGradeService;

    @GetMapping("/getGradeClass")
    public Response getGradeClass(){
        Response response = new Response();

        List<GradeClassVo> vos = stuGradeService.getGradeClass();
        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }

    @GetMapping("/getStuGrade")
    public Response getGrade(){
        Response response = new Response();

        List<StuGrade> list = stuGradeService.list();
        response.setData(list);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }

}

