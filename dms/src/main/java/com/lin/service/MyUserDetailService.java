package com.lin.service;

import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//@Component  工厂扫描到这个类就会创建一个对象
@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public MyUserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if(ObjectUtils.isEmpty(user))throw new RuntimeException("用户不存在");
        user.setRoles(userMapper.getRolesByUid(user.getId()));
        return user;
    }
}
