package com.lin.mapper;

import com.lin.domain.vo.CascadeVo;
import com.lin.domain.vo.CourseNameVo;
import com.lin.domain.vo.CourseVo;
import com.lin.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.entity.StuClass;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-25
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> getByName(String name);


    List<CourseVo> getCourseList();

    List<CourseNameVo> getCourseSelect();

    List<CascadeVo> getCascade();
}
