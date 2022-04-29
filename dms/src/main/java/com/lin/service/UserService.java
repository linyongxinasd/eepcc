package com.lin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.domain.vo.UserVo;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-15
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    @Resource
    UserMapper userMapper;
    public List<UserVo> getUserList(Long id) {

        return userMapper.getUserList(id);

    }


    /*public User getByUserName(String username){
        return getOne(new QueryWrapper<User>().eq("username",username));
    }*/
}
