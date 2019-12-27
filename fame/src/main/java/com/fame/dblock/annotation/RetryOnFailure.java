package com.fame.dblock.annotation;

import java.lang.annotation.*;

/**
 * @program: hello-world
 * @desc: 是否重试注解
 * @author: kervin
 * @time: 2019-12-26 16:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnFailure {
}
