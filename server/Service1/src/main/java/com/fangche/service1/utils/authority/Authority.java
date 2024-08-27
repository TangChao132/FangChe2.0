package com.fangche.service1.utils.authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//注解需作用在方法上
@Target(ElementType.METHOD)
//注解会保留到程序运行期
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    byte value() default Permission.NORMAL;

}
