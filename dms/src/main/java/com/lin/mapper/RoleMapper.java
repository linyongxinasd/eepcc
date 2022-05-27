package com.lin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户_角色表	 Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-15
 */
public interface RoleMapper extends BaseMapper<Role> {

    Page<Role> getByName(Page<Role> page,String name);
}
