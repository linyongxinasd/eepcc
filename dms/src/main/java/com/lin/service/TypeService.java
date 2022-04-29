package com.lin.service;

import com.lin.entity.Type;
import com.lin.mapper.TypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.codegen.TypeMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyx
 * @since 2022-04-26
 */
@Service
public class TypeService extends ServiceImpl<TypeMapper, Type>{


    @Resource
    public TypeMapper typeMapper;




}
