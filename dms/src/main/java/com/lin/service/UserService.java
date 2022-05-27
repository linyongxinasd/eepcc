package com.lin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.constant.Constant;
import com.lin.domain.param.PasswordParam;
import com.lin.domain.param.UserParam;
import com.lin.domain.vo.UserListVo;
import com.lin.domain.vo.UserVo;
import com.lin.entity.User;
import com.lin.entity.UserRole;
import com.lin.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<User> getList() {
        return userMapper.getList();
    }

    public List<User> getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Resource
    public UserRoleService userRoleService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    public Boolean saveUser(UserParam param) {

        if (null == param){
            return false;
        }else{

            String password = passwordEncoder.encode(Constant.DEFAULT_PASSWORD);
            User user = new User();
            user.setUsername(param.getUsername());
            user.setPassword(password);
            user.setName(param.getName());
            user.setGender(param.getGender());
            user.setAvatar(Constant.DEFAULT_AVATAR);
            user.setEnable(param.getEnable());
            int result = userMapper.saveUser(user);
            System.out.println("数量"+result);
            if (result >=1){
                User user1 = userMapper.loadUserByUsername(param.getUsername());

                List<UserRole> urList = new ArrayList<>();
                Long userId = user1.getId();
                List<Long> roleIds = param.getRoles();


                for (Long roleId: roleIds){

                    UserRole ur = new UserRole();
                    ur.setUserId(userId);
                    ur.setRoleId(roleId);
                    urList.add(ur);

                }
                return userRoleService.saveBatch(urList);
            }
            return false;

        }
    }

    public User getUserById(Long id) {

        return userMapper.getUserById(id);
    }

    public Boolean updateUserRole(UserParam param) {
        int result = userMapper.updateUser(param);
        System.out.println("更新用户信息result的值:"+result);
        Long userId = param.getId();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        userRoleService.remove(wrapper.eq("user_id",userId));

        List<UserRole> urList = new ArrayList<>();
        List<Long> roleIds = param.getRoles();
        for (Long roleId: roleIds){

            UserRole ur = new UserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            urList.add(ur);

        }
        boolean b = userRoleService.saveBatch(urList);

        return result >= 0 && b;
    }

    public Boolean delete(Long[] ids) {

        boolean re = removeBatchByIds(Arrays.asList(ids));

        boolean removeUserRole = false;
        for (Long id : ids) {
            QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
            removeUserRole = userRoleService.remove(wrapper.eq("user_id", id));
        }

        return re && removeUserRole;

    }

    public Boolean resetPassword(Long id) {


        String password = passwordEncoder.encode(Constant.DEFAULT_PASSWORD);
        int up = userMapper.updatePassWord(id,password);

        return up >= 1;
    }

    public boolean updatePassword(PasswordParam param, Principal principal) {

        User user = userMapper.loadUserByUsername(principal.getName());

        boolean matches = passwordEncoder.matches(param.getCurrentPassword(), user.getPassword());

        if (matches){
            String newPassword = passwordEncoder.encode(param.getNewPassword());
            int i = userMapper.updatePassWord(user.getId(), newPassword);
            return i >= 1;
        }
        return false;
    }


    /*public User getByUserName(String username){
        return getOne(new QueryWrapper<User>().eq("username",username));
    }*/
}
