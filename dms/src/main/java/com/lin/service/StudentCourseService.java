package com.lin.service;

import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.GradeAvg;
import com.lin.domain.vo.GradeVo;
import com.lin.domain.vo.ObjectiveGradeChartsVo;
import com.lin.domain.vo.StudentGradeVo;
import com.lin.entity.StudentCourse;
import com.lin.mapper.StudentCourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-27
 */
@Service
public class StudentCourseService extends ServiceImpl<StudentCourseMapper, StudentCourse>{

    @Resource
    public StudentCourseMapper studentCourseMapper;

    public List<StudentGradeVo> getCourseGradeList(CourseGradeParam param) {

        return studentCourseMapper.getCourseGradeList(param);
    }

    public List<GradeVo> getStudentGradeList(Long userId) {
        return  studentCourseMapper.getStudentGradeList(userId);
    }

    public List<ObjectiveGradeChartsVo> getObjective(CourseGradeParam param) {

        List<ObjectiveGradeChartsVo> vos = studentCourseMapper.getObjective(param);
        for (ObjectiveGradeChartsVo vo : vos) {
            vo.setObjectiveOneGrade(vo.getObjectiveOneGrade()/71);
            vo.setObjectiveTwoGrade(vo.getObjectiveTwoGrade()/21);
            vo.setObjectiveThreeGrade(vo.getObjectiveThreeGrade()/8);
        }
        return vos;
    }

    public GradeAvg getGradeAvg(CourseGradeParam param) {
        GradeAvg gradeAvg = studentCourseMapper.getGradeAvg(param);

        gradeAvg.setObjectiveOneGradeAvg(gradeAvg.getObjectiveOneGradeAvg()/71);
        gradeAvg.setObjectiveTwoGradeAvg(gradeAvg.getObjectiveTwoGradeAvg()/21);
        gradeAvg.setObjectiveThreeGradeAvg(gradeAvg.getObjectiveThreeGradeAvg()/8);

        return gradeAvg;
    }
}
