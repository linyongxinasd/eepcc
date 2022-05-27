package com.lin.domain.param;

import lombok.Data;

import java.util.List;

@Data
public class UserParam {


    private Long id;
    private String username;
    private String name;
    private String gender;
    List<Long> roles;
    private Boolean enable;
}
