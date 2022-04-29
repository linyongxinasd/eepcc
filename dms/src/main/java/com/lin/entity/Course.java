package com.lin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyx
 * @since 2022-04-25
 */
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程编号")
    private String courseNo;

    @ApiModelProperty("课程类型")
    private Long courseTypeId;

    @ApiModelProperty("课程总学时")
    private Integer creditHours;

    @ApiModelProperty("课程学分")
    private Double credit;

    @ApiModelProperty("开课班级")
    private Long stuClassId;

    @ApiModelProperty("开课学期")
    private Long semester;

    @ApiModelProperty("授课教师")
    private Long teacherId;

    @ApiModelProperty("评价教师")
    private Long evaluatorId;

    @ApiModelProperty("课程负责人")
    private Long directorId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(Integer creditHours) {
        this.creditHours = creditHours;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Long getStuClassId() {
        return stuClassId;
    }

    public void setStuClassId(Long stuClassId) {
        this.stuClassId = stuClassId;
    }

    public Long getSemester() {
        return semester;
    }

    public void setSemester(Long semester) {
        this.semester = semester;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(Long evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", courseName=" + courseName +
        ", courseNo=" + courseNo +
        ", courseTypeId=" + courseTypeId +
        ", creditHours=" + creditHours +
        ", credit=" + credit +
        ", stuClassId=" + stuClassId +
        ", semester=" + semester +
        ", teacherId=" + teacherId +
        ", evaluatorId=" + evaluatorId +
        ", directorId=" + directorId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
