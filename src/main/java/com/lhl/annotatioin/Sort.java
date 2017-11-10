package com.lhl.annotatioin;

import com.lhl.constants.SortRow;

import java.lang.annotation.*;

/**
 * 排序.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sort {
    /**
     * 字段名称.
     *
     * @return 字段名称
     */
    String name() default "";

    /**
     * 排序.
     * desc 倒序
     * asc 正序
     * 默认倒序排列
     *
     * @return 排序
     */
    SortRow order() default SortRow.DESC;

    /**
     * 排序得分.
     *
     * @return 排序得分
     */
    int socre() default 0;
}
