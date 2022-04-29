package com.lin.mapper;

import com.lin.domain.param.CourseGradeParam;
import com.lin.domain.vo.GradeVo;
import com.lin.domain.vo.ObjectiveGradeChartsVo;
import com.lin.domain.vo.StudentGradeVo;
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
}
