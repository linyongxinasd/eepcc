package com.lin.domain.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseGradeParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long semesterId;
    private Long classId;
}
