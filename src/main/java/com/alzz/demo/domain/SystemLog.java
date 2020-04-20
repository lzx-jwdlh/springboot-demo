package com.alzz.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com-alzz-demo-domain-SystemLog")
@Data
public class SystemLog implements Serializable {
    @ApiModelProperty(value = "null")
    private Integer logId;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String operCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private String logTime;

    /**
     * 操作模块
     */
    @ApiModelProperty(value = "操作模块")
    private String logModel;

    /**
     * 1：增加 2修改 3删除 4查询
     */
    @ApiModelProperty(value = "1：增加 2修改 3删除 4查询")
    private String logType;

    /**
     * sql
     */
    @ApiModelProperty(value = "sql")
    private String logContext;

    /**
     * S：成功 F失败
     */
    @ApiModelProperty(value = "S：成功 F失败")
    private String logResult;

    /**
     * 异常原因
     */
    @ApiModelProperty(value = "异常原因")
    private String logError;

    /**
     * 访问的url
     */
    @ApiModelProperty(value = "访问的url")
    private String logUrl;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    private String className;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    private String methodName;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String clientIp;

    private static final long serialVersionUID = 1L;
}