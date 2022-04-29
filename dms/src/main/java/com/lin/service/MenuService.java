package com.lin.service;

import cn.hutool.json.JSONUtil;
import com.lin.domain.vo.MenuVo;
import com.lin.entity.Menu;
import com.lin.entity.User;
import com.lin.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-25
 */
@Service
@Slf4j
public class MenuService extends ServiceImpl<MenuMapper, Menu>{

    @Autowired
    UserService userService;

    @Autowired
    public UserMapper userMapper;

    @Resource
    public MenuMapper menuMapper;

    public List<MenuVo> getCurrentUserNav(){

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userMapper.loadUserByUsername(username);

        List<Long> menuIds = menuMapper.getNavMenuIds(user.getId());
        List<Menu> menus = this.listByIds(menuIds);

        // 转树状结构
        List<Menu> menuTree = buildTreeMenu(menus);

        // 实体转DTO
        return convert(menuTree);


    }

    private List<MenuVo> convert(List<Menu> menuTree) {
        List<MenuVo> menus = new ArrayList<>();

        menuTree.forEach(m -> {
            MenuVo vo = new MenuVo();

            vo.setId(m.getId());
            vo.setName(m.getName());
            vo.setTitle(m.getTitle());
            vo.setComponent(m.getComponent());
            vo.setPath(m.getPath());
            vo.setIcon(m.getIcon());

            if (m.getChildren().size() > 0) {

                // 子节点调用当前方法进行再次转换
                vo.setChildren(convert(m.getChildren()));
            }

            menus.add(vo);
        });
        System.out.println(JSONUtil.toJsonStr(menus));
        return menus;
    }

    private List<Menu> buildTreeMenu(List<Menu> menus) {

        List<Menu> finalMenus = new ArrayList<>();

        // 先各自寻找到各自的孩子
        for (Menu menu : menus) {

            for (Menu e : menus) {
                if (menu.getId() == e.getParentId()) {
                    menu.getChildren().add(e);
                }
            }

            // 提取出父节点
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }

        System.out.println(JSONUtil.toJsonStr(finalMenus));
        return finalMenus;
    }

}
