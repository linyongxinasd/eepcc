package com.lin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyx
 * @since 2022-04-27
 */
@TableName("student_course")
@ApiModel(value = "StudentCourse对象", description = "")
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    private Long stuId;

    @ApiModelProperty("课程id")
    private Long courseId;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getObjectiveOneUsual() {
        return objectiveOneUsual;
    }

    public void setObjectiveOneUsual(Double objectiveOneUsual) {
        this.objectiveOneUsual = objectiveOneUsual;
    }

    public Double getObjectiveOneExperiment() {
        return objectiveOneExperiment;
    }

    public void setObjectiveOneExperiment(Double objectiveOneExperiment) {
        this.objectiveOneExperiment = objectiveOneExperiment;
    }

    public Double getObjectiveOneFinal() {
        return objectiveOneFinal;
    }

    public void setObjectiveOneFinal(Double objectiveOneFinal) {
        this.objectiveOneFinal = objectiveOneFinal;
    }

    public Double getObjectiveOneGrade() {
        return objectiveOneGrade;
    }

    public void setObjectiveOneGrade(Double objectiveOneGrade) {
        this.objectiveOneGrade = objectiveOneGrade;
    }

    public Double getObjectiveTwoUsual() {
        return objectiveTwoUsual;
    }

    public void setObjectiveTwoUsual(Double objectiveTwoUsual) {
        this.objectiveTwoUsual = objectiveTwoUsual;
    }

    public Double getObjectiveTwoExperiment() {
        return objectiveTwoExperiment;
    }

    public void setObjectiveTwoExperiment(Double objectiveTwoExperiment) {
        this.objectiveTwoExperiment = objectiveTwoExperiment;
    }

    public Double getObjectiveTwoFinal() {
        return objectiveTwoFinal;
    }

    public void setObjectiveTwoFinal(Double objectiveTwoFinal) {
        this.objectiveTwoFinal = objectiveTwoFinal;
    }

    public Double getObjectiveTwoGrade() {
        return objectiveTwoGrade;
    }

    public void setObjectiveTwoGrade(Double objectiveTwoGrade) {
        this.objectiveTwoGrade = objectiveTwoGrade;
    }

    public Double getObjectiveThreeUsual() {
        return objectiveThreeUsual;
    }

    public void setObjectiveThreeUsual(Double objectiveThreeUsual) {
        this.objectiveThreeUsual = objectiveThreeUsual;
    }

    public Double getObjectiveThreeExperiment() {
        return objectiveThreeExperiment;
    }

    public void setObjectiveThreeExperiment(Double objectiveThreeExperiment) {
        this.objectiveThreeExperiment = objectiveThreeExperiment;
    }

    public Double getObjectiveThreeFinal() {
        return objectiveThreeFinal;
    }

    public void setObjectiveThreeFinal(Double objectiveThreeFinal) {
        this.objectiveThreeFinal = objectiveThreeFinal;
    }

    public Double getObjectiveThreeGrade() {
        return objectiveThreeGrade;
    }

    public void setObjectiveThreeGrade(Double objectiveThreeGrade) {
        this.objectiveThreeGrade = objectiveThreeGrade;
    }

    public Double getUsualGrade() {
        return usualGrade;
    }

    public void setUsualGrade(Double usualGrade) {
        this.usualGrade = usualGrade;
    }

    public Double getExperimentGrade() {
        return experimentGrade;
    }

    public void setExperimentGrade(Double experimentGrade) {
        this.experimentGrade = experimentGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
        "id=" + id +
        ", stuId=" + stuId +
        ", courseId=" + courseId +
        ", objectiveOneUsual=" + objectiveOneUsual +
        ", objectiveOneExperiment=" + objectiveOneExperiment +
        ", objectiveOneFinal=" + objectiveOneFinal +
        ", objectiveOneGrade=" + objectiveOneGrade +
        ", objectiveTwoUsual=" + objectiveTwoUsual +
        ", objectiveTwoExperiment=" + objectiveTwoExperiment +
        ", objectiveTwoFinal=" + objectiveTwoFinal +
        ", objectiveTwoGrade=" + objectiveTwoGrade +
        ", objectiveThreeUsual=" + objectiveThreeUsual +
        ", objectiveThreeExperiment=" + objectiveThreeExperiment +
        ", objectiveThreeFinal=" + objectiveThreeFinal +
        ", objectiveThreeGrade=" + objectiveThreeGrade +
        ", usualGrade=" + usualGrade +
        ", experimentGrade=" + experimentGrade +
        ", finalGrade=" + finalGrade +
        ", totalGrade=" + totalGrade +
        "}";
    }
}
