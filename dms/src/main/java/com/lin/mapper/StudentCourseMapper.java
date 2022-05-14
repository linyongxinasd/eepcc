package com.lin.mapper;

import com.lin.domain.ObjectiveBelowNums;
import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.*;
import com.lin.entity.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-27
 */
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {


    List<StudentGradeVo> getCourseGradeList(@Param("param") CourseGradeParam param);

    List<GradeVo> getStudentGradeList(Long userId);

    List<ObjectiveGradeChartsVo> getObjective(@Param("param") CourseGradeParam param);

    GradeAvg getGradeAvg(@Param("param") CourseGradeParam param);

    void saveBathGrade(List<StudentCourse> list);


    ObjectiveBelowNums getObjectiveBelowNums(@Param("param") CourseGradeParam param);
}
