package com.lin.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GradeCascadeVo {

    private Long id;
    private String name;

    private List<ClassCascadeVo> children = new ArrayList<>();
}
