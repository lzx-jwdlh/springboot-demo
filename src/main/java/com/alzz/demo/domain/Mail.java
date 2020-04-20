package com.alzz.demo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Mail {

    /**
     * 发给多个人
     */
    private String[] to;

    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容   简单文本 和附件邮件必填  其余的不需要
     */
    private String text;

    /**
     * 模板需要的数据   发送模板邮件必填
     */
    private Map<String,String> templateModel;

    /**
     * 选用哪个模板 发送模板邮件必填
     */
    private String templateName;

}
