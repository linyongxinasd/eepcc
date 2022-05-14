package com.lin.service;

import com.lin.domain.ObjectiveBelowNums;
import com.lin.domain.ReportMessage;
import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.*;
import com.lin.entity.StudentCourse;
import com.lin.mapper.StudentCourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.corba.se.impl.oa.toa.TOA;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-27
 */
@Service
public class StudentCourseService extends ServiceImpl<StudentCourseMapper, StudentCourse> {

    @Resource
    public StudentCourseMapper studentCourseMapper;

    public List<StudentGradeVo> getCourseGradeList(CourseGradeParam param) {

        return studentCourseMapper.getCourseGradeList(param);
    }

    public List<GradeVo> getStudentGradeList(Long userId) {
        return studentCourseMapper.getStudentGradeList(userId);
    }

    public List<ObjectiveGradeChartsVo> getObjective(CourseGradeParam param) {

        List<ObjectiveGradeChartsVo> vos = studentCourseMapper.getObjective(param);
        for (ObjectiveGradeChartsVo vo : vos) {
            vo.setObjectiveOneGrade(vo.getObjectiveOneGrade() / 71);
            vo.setObjectiveTwoGrade(vo.getObjectiveTwoGrade() / 21);
            vo.setObjectiveThreeGrade(vo.getObjectiveThreeGrade() / 8);
        }
        return vos;
    }

    public GradeAvg getGradeAvg(CourseGradeParam param) {
        GradeAvg gradeAvg = studentCourseMapper.getGradeAvg(param);

        gradeAvg.setObjectiveOneGradeAvg(gradeAvg.getObjectiveOneGradeAvg() / 71);
        gradeAvg.setObjectiveTwoGradeAvg(gradeAvg.getObjectiveTwoGradeAvg() / 21);
        gradeAvg.setObjectiveThreeGradeAvg(gradeAvg.getObjectiveThreeGradeAvg() / 8);

        return gradeAvg;
    }

    public void save(List<GradeExcel> lists,Long courseId) {

        List<StudentCourse> gradeLists = new ArrayList<>();

        for (GradeExcel list : lists) {
            StudentCourse grade = new StudentCourse();

            grade.setStuId(list.getStuId());
            grade.setCourseId(courseId);

            grade.setObjectiveOneUsual(list.getObjectiveOneUsual());
            grade.setObjectiveOneExperiment(list.getObjectiveOneExperiment());
            grade.setObjectiveOneFinal(list.getObjectiveOneFinal());
            grade.setObjectiveOneGrade(getObjectiveGrade(grade.getObjectiveOneUsual(), grade.getObjectiveOneExperiment(), grade.getObjectiveOneFinal()));


            grade.setObjectiveTwoUsual(list.getObjectiveTwoUsual());
            grade.setObjectiveTwoExperiment(list.getObjectiveTwoExperiment());
            grade.setObjectiveTwoFinal(list.getObjectiveTwoFinal());
            grade.setObjectiveTwoGrade(getObjectiveGrade(grade.getObjectiveTwoUsual(), grade.getObjectiveTwoExperiment(), grade.getObjectiveTwoFinal()));


            grade.setObjectiveThreeExperiment(list.getObjectiveThreeExperiment());
            grade.setObjectiveThreeGrade(getObjectThreeGrade(grade.getObjectiveThreeExperiment()));


            grade.setUsualGrade(gradeSum(grade.getObjectiveOneUsual(), grade.getObjectiveTwoUsual()));
            grade.setExperimentGrade(gradeSumTotal(grade.getObjectiveOneExperiment(), grade.getObjectiveTwoExperiment(), grade.getObjectiveThreeExperiment()));
            grade.setFinalGrade(gradeSum(grade.getObjectiveOneFinal(),grade.getObjectiveTwoFinal()));

            grade.setTotalGrade(getObjectiveGrade(grade.getUsualGrade(), grade.getExperimentGrade(), grade.getFinalGrade()));

            gradeLists.add(grade);
            
        }


        saveBatch(gradeLists);
    }

    public ObjectiveBelowNums getObjectiveBelowNums(CourseGradeParam param){
        return studentCourseMapper.getObjectiveBelowNums(param);
    }

    public ReportVo getCourseReport(CourseGradeParam param) {
        GradeAvg gradeAvg = getGradeAvg(param);

        BigDecimal gradeOne = BigDecimal.valueOf(gradeAvg.getObjectiveOneGradeAvg());
        BigDecimal gradeTwo = BigDecimal.valueOf(gradeAvg.getObjectiveTwoGradeAvg());
        BigDecimal gradeThree = BigDecimal.valueOf(gradeAvg.getObjectiveThreeGradeAvg());
        double one = gradeOne.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double two = gradeTwo.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double three = gradeThree.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        String objectOneReport = "(1)达成情况："+ReportMessage.SEE_FROM_PICTURE+ReportMessage.AVERAGE_ACHIEVEMENT_ONE+one+ReportMessage.GOAL_ACHIEVEMENT;
        if (gradeAvg.getObjectiveOneGradeAvg()>=0.6){
            objectOneReport +=ReportMessage.ACHIEVED_ONE+"</br>";
        } else {
            objectOneReport += ReportMessage.NOT_ACHIEVED_ONE+"</br>";
        }
        ObjectiveBelowNums nums = getObjectiveBelowNums(param);
        objectOneReport += "(2)问题分析："+ReportMessage.ANALYSIS_PRE+nums.getObjectOneBelow()+ReportMessage.ACHIEVEMENT_BELOW_GOAL_ONE
                +ReportMessage.ACHIEVEMENT_TOO_LOW;

        String objectTwoReport =  "(1)达成情况："+ReportMessage.SEE_FROM_PICTURE+ReportMessage.AVERAGE_ACHIEVEMENT_TWO+two+ReportMessage.GOAL_ACHIEVEMENT;
        if (gradeAvg.getObjectiveTwoGradeAvg()>=0.6){
            objectTwoReport +=ReportMessage.ACHIEVED_TWO+"</br>";
        } else {
            objectTwoReport += ReportMessage.NOT_ACHIEVED_TWO+"</br>";
        }
        objectTwoReport += "(2)问题分析："+ReportMessage.ANALYSIS_PRE+nums.getObjectTwoBelow()+ReportMessage.ACHIEVEMENT_BELOW_GOAL_TWO
                +ReportMessage.ACHIEVEMENT_TOO_LOW;

        String objectThreeReport = "(1)达成情况："+ReportMessage.SEE_FROM_PICTURE+ReportMessage.AVERAGE_ACHIEVEMENT_THREE+three+ReportMessage.GOAL_ACHIEVEMENT;
        if (gradeAvg.getObjectiveThreeGradeAvg()>=0.6){
            objectThreeReport +=ReportMessage.ACHIEVED_THREE+"</br>";
        } else {
            objectThreeReport += ReportMessage.NOT_ACHIEVED_THREE+"</br>";
        }
        objectThreeReport += "(2)问题分析："+ReportMessage.ANALYSIS_PRE+nums.getObjectThreeBelow()+ReportMessage.ACHIEVEMENT_BELOW_GOAL_THREE
                +ReportMessage.ACHIEVEMENT_TOO_LOW;

        String totalReport = "评价结果：</br>"+ReportMessage.SEE_FROM_PICTURE+ReportMessage.AVERAGE_ACHIEVEMENT_ONE+one+"。"+ReportMessage.AVERAGE_ACHIEVEMENT_TWO+two+"。"+ReportMessage.AVERAGE_ACHIEVEMENT_THREE+three+"。";
        if (one < 0.6){
            totalReport += ReportMessage.NOT_ACHIEVED_ONE;
        } else if (two <0.6){
            totalReport += ReportMessage.NOT_ACHIEVED_TWO;

        } else if (three < 0.6){
            totalReport += ReportMessage.NOT_ACHIEVED_THREE;

        } else{
            totalReport += ReportMessage.TOTAL_ACHIEVED;
        }
        double gradeAverageSum = gradeAvg.getObjectiveOneGradeAvg()+ gradeAvg.getObjectiveTwoGradeAvg()+gradeAvg.getObjectiveThreeGradeAvg();
        BigDecimal gradeAverage = BigDecimal.valueOf(gradeAverageSum/3);
        double average = gradeAverage.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        totalReport += ReportMessage.AVERAGE_ACHIEVEMENT_TOTAL+average;
        if (average >= 0.6){
            totalReport += ReportMessage.ACHIEVED+"</br>";
        } else {
            totalReport += ReportMessage.NOT_ACHIEVED+"</br>";
        }
        /*totalReport += "(2)问题分析：</br>"+*/
        ReportVo vo = new ReportVo();
        vo.setObjectOneReport(objectOneReport);
        vo.setObjectTwoReport(objectTwoReport);
        vo.setObjectThreeReport(objectThreeReport);
        vo.setTotalReport(totalReport);
        return vo;
    }

    public static Double getObjectiveGrade(Double usual, Double experiment, Double finalGrade) {
        return usual * 0.1 + experiment * 0.2 + finalGrade * 0.7;
    }
    public static Double getObjectThreeGrade(Double grade){
        return grade*0.2;
    }

    public static Double gradeSum(Double one,  Double three) {
        return one + three;
    }
    public static Double gradeSumTotal(Double one, Double two, Double three) {
        return one + two + three;
    }

}
