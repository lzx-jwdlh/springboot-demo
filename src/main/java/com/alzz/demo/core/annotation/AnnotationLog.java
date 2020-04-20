package com.alzz.demo.core.annotation;

import java.lang.annotation.*;

/**
 * @ClassName AnnotationLog
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/14 15:52
 */
@Target(ElementType.METHOD) //定义注解的范围
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {
    String remark() default "";
}
