package com.lin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyx
 * @since 2022-04-26
 */
@TableName("stu_class")
@ApiModel(value = "StuClass对象", description = "")
public class StuClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("班级名称")
    private String name;

    @ApiModelProperty("所属年级ID")
    private Long stuGradeId;


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

    public Long getStuGradeId() {
        return stuGradeId;
    }

    public void setStuGradeId(Long stuGradeId) {
        this.stuGradeId = stuGradeId;
    }

    @Override
    public String toString() {
        return "StuClass{" +
        "id=" + id +
        ", name=" + name +
        ", stuGradeId=" + stuGradeId +
        "}";
    }
}
