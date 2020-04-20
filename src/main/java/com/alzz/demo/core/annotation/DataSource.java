package com.alzz.demo.core.annotation;



import java.lang.annotation.*;

/**
 * @ClassName DataSource
 * @Description TODO 切换数据注解 可以用于类或者方法级别 方法级别优先级 > 类级别
 * @Author lzx
 * @Date 2020/1/16 9:44
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default "master"; //该值即key值
}
