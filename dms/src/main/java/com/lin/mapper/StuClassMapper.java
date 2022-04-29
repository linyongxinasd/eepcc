package com.lin.mapper;

import com.lin.entity.StuClass;
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
public interface StuClassMapper extends BaseMapper<StuClass> {

    List<StuClass> getList();
}
