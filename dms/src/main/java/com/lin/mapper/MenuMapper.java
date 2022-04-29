package com.lin.mapper;

import com.lin.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-25
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> getNavMenuIds(Long userId);

}
