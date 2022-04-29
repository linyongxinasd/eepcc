package com.lin.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CourseVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程编号")
    private String courseNo;

    @ApiModelProperty("课程类型")
    private String courseType;

    @ApiModelProperty("课程总学时")
    private Integer creditHours;

    @ApiModelProperty("课程学分")
    private Double credit;

    @ApiModelProperty("开课班级")
    private String stuClass;

    @ApiModelProperty("开课年级")
    private String stuGrade;

    @ApiModelProperty("开课学期")
    private String semester;

    @ApiModelProperty("开课老师")
    private String teacher;

    @ApiModelProperty("评价老师")
    private String evaluator;

    @ApiModelProperty("课程负责人")
    private String director;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
