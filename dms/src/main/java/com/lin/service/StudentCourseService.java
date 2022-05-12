package com.lin.service;

import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.*;
import com.lin.entity.StudentCourse;
import com.lin.mapper.StudentCourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
