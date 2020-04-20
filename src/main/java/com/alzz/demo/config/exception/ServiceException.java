package com.alzz.demo.config.exception;

import java.io.Serializable;

/**
 * @ClassName ServiceException
 * @Description 业务类异常
 * @Author lzx
 * @Date 2020/1/10 11:32
 */
public class ServiceException extends RuntimeException implements Serializable {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
