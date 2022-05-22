package com.lin.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户登录名")
    private String name;
}
