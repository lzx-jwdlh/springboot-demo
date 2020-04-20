package com.alzz.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com-alzz-demo-domain-SysRole")
@Data
public class SysRole implements Serializable {
    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称")
    private String id;

    /**
    * 角色名称，用于显示
    */
    @ApiModelProperty(value="角色名称，用于显示")
    private String roleName;

    /**
    * 角色描述
    */
    @ApiModelProperty(value="角色描述")
    private String roleDesc;

    /**
    * 角色值，用于权限判断
    */
    @ApiModelProperty(value="角色值，用于权限判断")
    private String roleValue;

    @ApiModelProperty(value="null")
    private Date createTime;

    @ApiModelProperty(value="null")
    private Date updateTime;

    /**
    * 是否禁用
    */
    @ApiModelProperty(value="是否禁用")
    private Integer isDisable;

    private static final long serialVersionUID = 1L;
}