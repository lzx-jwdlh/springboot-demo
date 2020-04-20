package com.alzz.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com-alzz-demo-domain-SysPermissionInit")
@Data
public class SysPermissionInit implements Serializable {
    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 程序对应url地址
     */
    @ApiModelProperty(value = "程序对应url地址")
    private String url;

    /**
     * 对应shiro权限
     */
    @ApiModelProperty(value = "对应shiro权限")
    private String permissionInit;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}