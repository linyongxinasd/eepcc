package com.lin.domain.vo;

import com.lin.entity.StuClass;
import com.lin.entity.StuGrade;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GradeClassVo {

    private Long id;

    private String name;

    private List<StuClass> classes = new ArrayList<>();


}
