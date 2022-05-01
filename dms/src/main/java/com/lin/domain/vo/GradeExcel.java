package com.lin.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


public class GradeExcel {

    @Override
    public String toString() {
        return "GradeExcel{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", courseType='" + courseType + '\'' +
                ", semester='" + semester + '\'' +
                ", credit=" + credit +
                ", totalGrade=" + totalGrade +
                '}';
    }

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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("课程名称")
    private String courseName;

    @ExcelProperty("课程编号")
    private String courseNo;

    @ExcelProperty("课程类型")
    private String courseType;

    @ExcelProperty("开课学期")
    private String semester;

    @ExcelProperty("课程学分")
    private Double credit;

    @ExcelProperty("总评成绩")
    private Double totalGrade;
}
