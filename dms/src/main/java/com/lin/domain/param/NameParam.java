package com.lin.domain.param;

import lombok.Data;

@Data
public class NameParam {

    //当前页
    private int current;

    //当前页数据量
    private int size;

    private String name;
}
