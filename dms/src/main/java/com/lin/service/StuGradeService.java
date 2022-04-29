package com.lin.service;

import com.lin.domain.vo.GradeClassVo;
import com.lin.entity.StuGrade;
import com.lin.mapper.StuGradeMapper;
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
public class StuGradeService extends ServiceImpl<StuGradeMapper, StuGrade>{

    @Resource
    public StuGradeMapper stuGradeMapper;

    public List<GradeClassVo> getGradeClass() {

        return stuGradeMapper.getGradeClass();

    }
}
