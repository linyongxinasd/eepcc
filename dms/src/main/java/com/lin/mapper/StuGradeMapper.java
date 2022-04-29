package com.lin.mapper;

import com.lin.domain.vo.GradeClassVo;
import com.lin.entity.StuGrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-26
 */
public interface StuGradeMapper extends BaseMapper<StuGrade> {

    List<GradeClassVo> getGradeClass();
}
