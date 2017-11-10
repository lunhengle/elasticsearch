package com.lhl.annotatioin;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Index {
    /**
     * 索引.
     *
     * @return 类型
     */
    String index() default "";

    /**
     * 类型.
     *
     * @return 索引
     */
    String type() default "";

}
