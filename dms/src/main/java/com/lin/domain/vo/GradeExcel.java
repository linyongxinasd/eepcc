package com.lin.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.lin.utils.MyNumberToLongConvert;
import lombok.Data;

@Data
@ColumnWidth(8)
public class GradeExcel {



    @ExcelProperty(value = "序号",index = 0)
    private Long id;

    @ColumnWidth(16)
    @ExcelProperty(value = "学生学号",index = 1)
    private Long stuId;
    @ColumnWidth(8)
    @ExcelProperty(value = "学生姓名",index = 2)
    private String stuName;

    @ExcelProperty(value = "课程id",index = 3)
    private Long courseId;

    @ExcelProperty(value = {"目标一","课堂表现"},index = 4)
    private Double objectiveOneUsual;

    @ExcelProperty(value = {"目标一","实验作业"},index = 5)
    private Double objectiveOneExperiment;

    @ExcelProperty(value = {"目标一","期末考试"},index = 6)
    private Double objectiveOneFinal;

/*    @ExcelProperty({"目标一","课程目标得分"})
    private Double objectiveOneGrade;*/

    @ExcelProperty(value = {"目标二","课堂表现"},index = 7)
    private Double objectiveTwoUsual;

    @ExcelProperty(value = {"目标二","实验作业"},index = 8)
    private Double objectiveTwoExperiment;

    @ExcelProperty(value = {"目标二","期末考试"},index = 9)
    private Double objectiveTwoFinal;

  /*  @ExcelProperty({"目标二","课程目标得分"})
    private Double objectiveTwoGrade;*/

    @ExcelProperty(value = {"目标三","课堂表现"},index = 10)
    private Double objectiveThreeUsual;

    @ExcelProperty(value = {"目标三","实验作业"},index = 11)
    private Double objectiveThreeExperiment;

    @ExcelProperty(value = {"目标三","期末考试"},index = 12)
    private Double objectiveThreeFinal;
/*
    @ExcelProperty({"目标三","课程目标得分"})
    private Double objectiveThreeGrade;*/


}
