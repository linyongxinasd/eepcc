package com.lin.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentGradeVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("目标一课堂表现成绩")
    private Double objectiveOneUsual;

    @ApiModelProperty("目标一实验作业成绩")
    private Double objectiveOneExperiment;

    @ApiModelProperty("目标一期末考试成绩")
    private Double objectiveOneFinal;

    @ApiModelProperty("目标一课程目标得分")
    private Double objectiveOneGrade;

    @ApiModelProperty("目标二课堂表现成绩")
    private Double objectiveTwoUsual;

    @ApiModelProperty("目标二实验作业成绩")
    private Double objectiveTwoExperiment;

    @ApiModelProperty("目标二期末考试成绩")
    private Double objectiveTwoFinal;

    @ApiModelProperty("目标二课程目标得分")
    private Double objectiveTwoGrade;

    @ApiModelProperty("目标三课堂表现成绩")
    private Double objectiveThreeUsual;

    @ApiModelProperty("目标三实验作业成绩")
    private Double objectiveThreeExperiment;

    @ApiModelProperty("目标三期末考试成绩")
    private Double objectiveThreeFinal;

    @ApiModelProperty("目标三课程目标得分")
    private Double objectiveThreeGrade;

    @ApiModelProperty("平时表现成绩")
    private Double usualGrade;

    @ApiModelProperty("实验作业成绩")
    private Double experimentGrade;

    @ApiModelProperty("期末考试成绩")
    private Double finalGrade;

    @ApiModelProperty("总评成绩")
    private Double totalGrade;
}
