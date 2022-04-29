package com.lin.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseNameVo {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("课程名称")
    private String courseName;
}
