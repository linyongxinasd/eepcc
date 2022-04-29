package com.lin.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GradeVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程编号")
    private String courseNo;

    @ApiModelProperty("课程类型")
    private String courseType;

    @ApiModelProperty("开课学期")
    private String semester;

    @ApiModelProperty("课程学分")
    private Double credit;

    @ApiModelProperty("总评成绩")
    private Double totalGrade;



}
