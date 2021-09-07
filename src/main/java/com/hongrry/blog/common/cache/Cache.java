package com.hongrry.blog.common.cache;

import java.lang.annotation.*;

/**
 * @author Hongrry
 * @create 2021-09-05 18:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 60 * 1000;

    String name() default "";

}
