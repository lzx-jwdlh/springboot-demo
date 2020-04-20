package com.alzz.demo.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;

@ApiModel(value = "com-alzz-demo-domain-User")
@Data
public class User  implements Serializable {
    @ApiModelProperty(value = "null")
    @ExcelProperty("id")
    private Long id;

    @ApiModelProperty(value = "null")
    @ExcelProperty("名字")
    private String userName;

    @ApiModelProperty(value = "null")
    @ExcelProperty("密码")
    private String password;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */

    @Transient
    @ExcelIgnore
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    @ExcelIgnore
    private Set<String> perms;

    private static final long serialVersionUID = 1L;
}