package com.lin.service;

import com.lin.domain.vo.CourseNameVo;
import com.lin.domain.vo.CourseVo;
import com.lin.entity.Course;
import com.lin.mapper.CourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-25
 */
@Service
public class CourseService extends ServiceImpl<CourseMapper, Course>{

    @Resource
    public CourseMapper courseMapper;

    public void delete(Long[] ids) {


    }

    public List<CourseVo> getByName(String name) {

        return courseMapper.getByName(name);
    }

    public List<CourseVo> getCourseList() {

        return courseMapper.getCourseList();
    }

    public List<CourseNameVo> getCourseSelect() {

        return courseMapper.getCourseSelect();
    }
}
