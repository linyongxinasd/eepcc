package com.lin.controller;


import com.alibaba.excel.EasyExcel;
import com.lin.config.listener.MyReadListener;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.vo.GradeExcel;
import com.lin.mapper.StudentCourseMapper;
import com.lin.service.StudentCourseService;
import com.lin.utils.MyExcelUtil;
import com.lin.utils.MyNumberToLongConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ExcelController {


    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        List<GradeExcel> list = new ArrayList<>();
        String name = "课程成绩模板";
        MyExcelUtil.DefaultExcel(response,name,GradeExcel.class,list);


    }

    @Resource
    public StudentCourseService studentCourseService;
    @PostMapping("/upload")
    public Response upload(MultipartFile file) throws IOException {
        Response response = new Response();

        EasyExcel.read(file.getInputStream(), GradeExcel.class, new MyReadListener(studentCourseService)).registerConverter(new MyNumberToLongConvert()).sheet().doRead();
        response.setStatusCode(ResponseCode.SUCCESS);

        return response;
    }
}
