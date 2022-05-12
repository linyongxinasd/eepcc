package com.lin.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassCascadeVo {

    private Long id;

    private String name;

    private List<CourseCascadeVo> children = new ArrayList<>();
}
