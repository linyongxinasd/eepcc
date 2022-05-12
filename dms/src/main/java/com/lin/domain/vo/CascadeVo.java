package com.lin.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CascadeVo {
    private Long id;
    private String name;

    private List<GradeCascadeVo> children = new ArrayList<>();
}
