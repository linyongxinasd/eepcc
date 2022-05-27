package com.lin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Role;
import com.lin.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户_角色表	 服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-15
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role>{


    @Resource
    public RoleMapper roleMapper;
    public Page<Role> getByName(String name, Long current, Long size) {

        Page<Role> rolePage = new Page<>();
        rolePage.setCurrent(current);
        rolePage.setSize(size);
        Page<Role> roleList = roleMapper.getByName(rolePage,name);
        rolePage.setRecords(roleList.getRecords());
        rolePage.setTotal(roleList.getTotal());

        return rolePage;

    }
}
