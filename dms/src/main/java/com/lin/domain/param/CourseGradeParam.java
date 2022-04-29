package com.lin.domain.param;

import lombok.Data;

@Data
public class CourseGradeParam {

    private String name;
    private Long semesterId;
    private Long classId;
}
