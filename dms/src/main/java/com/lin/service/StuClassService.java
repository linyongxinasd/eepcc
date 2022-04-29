package com.lin.service;

import com.lin.entity.StuClass;
import com.lin.mapper.CourseMapper;
import com.lin.mapper.StuClassMapper;
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
 * @since 2022-04-26
 */
@Service
public class StuClassService extends ServiceImpl<StuClassMapper, StuClass>{

    @Resource
    StuClassMapper stuClassMapper;
    public List<StuClass> getStuClassList() {

        return stuClassMapper.getList();


    }
}
