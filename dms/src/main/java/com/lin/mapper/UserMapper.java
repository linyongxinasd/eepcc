package com.lin.mapper;

import com.lin.domain.vo.UserVo;
import com.lin.entity.Role;
import com.lin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lyx
 * @since 2022-04-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(String username);

    List<Role> getRolesByUid(Long uid);


    List<UserVo> getUserList(Long id);
}
