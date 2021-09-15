package com.hongrry.blog.api.common.aop.annotation;


import java.lang.annotation.*;

/**
 * @author Hongrry
 * @create 2021-09-04 10:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operation() default "";
}
