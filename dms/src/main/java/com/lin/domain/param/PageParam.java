package com.lin.domain.param;

import lombok.Data;

@Data
public class PageParam {

    //当前页
    private int current = 1;

    //当前页数据量
    private int size = 10;
}
