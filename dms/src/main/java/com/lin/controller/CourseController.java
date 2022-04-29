package com.lin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.vo.CourseNameVo;
import com.lin.domain.vo.CourseVo;
import com.lin.entity.Course;
import com.lin.mapper.CourseMapper;
import com.lin.service.CourseService;
import com.lin.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/course")
public class CourseController {


    @Resource
    public CourseMapper courseMapper;

    @Resource
    public CourseService courseService;

    @GetMapping("/getCourse")
    public Response getCourse(){
        Response response = new Response();

        //List<Course> course = courseService.list();
        List<CourseVo> course = courseService.getCourseList();


        response.setData(course);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }

    @GetMapping("/info/{id}")
    public Response info(@PathVariable Long id){
        Response response = new Response();

        Course course = courseService.getById(id);

        response.setData(course);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }

    @PostMapping("/save")
    public Response save(@RequestBody Course course){
        Response response = new Response();
        boolean save = courseService.saveOrUpdate(course);

        if (save){
            response.setResponseBySuccessMsg("操作成功");
        }else {
            response.setResponseByErrorMsg("操作失败");
        }

        return response;
    }

    @PostMapping("/update")
    public Response update(@RequestBody Course course){
        Response response = new Response();

        log.info("NAME:"+course.getCourseName());
        System.out.println("TYPE:"+course.getCourseTypeId());


        return response;
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        Response response = new Response();

        //courseService.delete(ids);

        courseService.removeBatchByIds(Arrays.asList(ids));

        return response;
    }

    @GetMapping("/getByName")
    Response getByName(@RequestParam String name){
        Response response = new Response();
        List<CourseVo> course = courseService.getByName(name);

        response.setData(course);
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;

    }

    @GetMapping("/getCourseSelect")
    public Response getCourseSelect(){

        Response response = new Response();

        List<CourseNameVo> vos = courseService.getCourseSelect();
        response.setData(vos);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;


    }
}
