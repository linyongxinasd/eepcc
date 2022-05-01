package com.lin.controller;


import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.GradeAvg;
import com.lin.domain.vo.GradeVo;
import com.lin.domain.vo.ObjectiveGradeChartsVo;
import com.lin.domain.vo.StudentGradeVo;
import com.lin.entity.User;
import com.lin.mapper.CourseMapper;
import com.lin.mapper.StudentCourseMapper;
import com.lin.mapper.UserMapper;
import com.lin.service.StudentCourseService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyx
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {

    @Resource
    public StudentCourseMapper studentCourseMapper;

    @Resource
    public StudentCourseService studentCourseService;

    @Resource
    public CourseMapper courseMapper;

    @Resource
    public UserMapper userMapper;


    /*@GetMapping("/getCourseGrade")
    public Response getCourseGrade(@RequestParam String name){
        Response response = new Response();

        List<StudentGradeVo> vos = studentCourseService.getCourseGradeList(name);

        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;


    }*/

    @GetMapping("/getStudentGrade")
    public Response getStudentGrade(Principal principal){
        Response response = new Response();

        User user = userMapper.loadUserByUsername(principal.getName());

        List<GradeVo> vos = studentCourseService.getStudentGradeList(user.getId());

        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);


        return response;
    }

    @PostMapping("/getByRequire")
    public Response getByRequire(@RequestBody CourseGradeParam param){
        Response response = new Response();


        List<StudentGradeVo> vos = studentCourseService.getCourseGradeList(param);

        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);


        return response;
    }

    @PostMapping("/getObjective")
    public Response getObjectiveOne(@RequestBody CourseGradeParam param){

        Response response = new Response();

        List<ObjectiveGradeChartsVo> vos = studentCourseService.getObjective(param);
        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;
    }

    @PostMapping("/getGradeAvg")
    public Response getGradeAvg(@RequestBody CourseGradeParam param){
        Response response = new Response();

        GradeAvg gradeAvg = studentCourseService.getGradeAvg(param);

        response.setData(gradeAvg);
        response.setStatusCode(ResponseCode.SUCCESS);


        return response;
    }



}

