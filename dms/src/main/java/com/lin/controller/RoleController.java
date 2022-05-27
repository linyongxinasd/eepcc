package com.lin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.domain.Response;
import com.lin.domain.ResponseCode;
import com.lin.domain.param.PageParam;
import com.lin.entity.Role;
import com.lin.mapper.RoleMapper;
import com.lin.mapper.UserMapper;
import com.lin.service.RoleService;
import com.lin.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    public RoleMapper roleMapper;
    @Resource
    public RoleService roleService;

    @PostMapping("/getRoleList")
    public Response getRoleList(@RequestBody PageParam param){
        Response response = new Response();

        Page<Role> page = new Page<>(param.getCurrent(),param.getSize());
        Page<Role> roleList = roleService.page(page);
        response.setData(roleList);
        return response;
    }

    @GetMapping("/getEnableRoleList")
    public Response getEnableRoleList(){
        Response response = new Response();
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        List<Role> roleList = roleService.list(wrapper.eq("status",1));
        response.setData(roleList);
        return response;
    }

    @GetMapping("/getByName")
    public Response getByName(@RequestParam String name,Long current,Long size){
        Response response = new Response();

        Page<Role> roleList = roleService.getByName(name,current,size);
        response.setData(roleList);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;
    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable Long id){
        Response response = new Response();

        Role role = roleService.getById(id);
        response.setData(role);
        response.setStatusCode(ResponseCode.SUCCESS);
        return response;
    }

    @PostMapping("/saveOrUpdate")
    public Response saveOrUpdate(@RequestBody Role role){
        Response response = new Response();

        boolean save = roleService.saveOrUpdate(role);

        if (save) {
            response.setResponseBySuccessMsg("添加角色成功！");
        } else {
            response.setResponseByErrorMsg("添加角色失败！");
        }

        return response;
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        Response response = new Response();

        boolean b = roleService.removeBatchByIds(Arrays.asList(ids));

        if (b){
            response.setResponseBySuccessMsg("删除成功！");
        } else {
            response.setResponseByErrorMsg("删除失败！");
        }
        return response;
    }

}
