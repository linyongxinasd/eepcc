package com.lin.controller;

import cn.hutool.core.map.MapUtil;
import com.lin.domain.Response;
import com.lin.domain.vo.MenuVo;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.MenuService;
import com.lin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    public UserService userService;

    @Autowired
    public MenuService menuService;

    @Resource
    public UserMapper userMapper;

    @GetMapping("/nav")
    public Response getMenu(Principal principal){
        Response response = new Response();

        User user = userMapper.loadUserByUsername(principal.getName());

        List<MenuVo> navs = menuService.getCurrentUserNav();

        String[] authoritys = {"sys:user:list", "sys:user:save", "sys:user:delete"};
        response.setData(MapUtil.builder()
                .put("authoritys", authoritys)
                .put("nav", navs)
                .map());
        return response;
    }
}
