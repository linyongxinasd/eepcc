package com.lin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户_角色表	
 * </p>
 *
 * @author lyx
 * @since 2022-04-15
 */
@ApiModel(value = "Role对象", description = "用户_角色表	")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否系统角色")
    private Boolean isSystemRole;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSystemRole() {
        return isSystemRole;
    }

    public void setIsSystemRole(Boolean isSystemRole) {
        this.isSystemRole = isSystemRole;
    }

    @Override
    public String toString() {
        return "Role{" +
        "id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", isSystemRole=" + isSystemRole +
        "}";
    }
}
