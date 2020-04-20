package com.alzz.demo.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ResponseBean
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/10 11:54
 */
@Data
@Accessors(chain = true)
public class ResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 3814193255651633369L;

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private T data;

    public ResponseBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseBean( T data) {
        this.code = 0;
        this.msg = "操作成功";
        this.data = data;
    }
    public ResponseBean() {
        this.code = 0;
        this.msg = "操作成功";
    }

    public ResponseBean(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public ResponseBean<T> setCode(ResponseCode responseCode) {
        this.code = responseCode.code;
        return this;
    }

}

