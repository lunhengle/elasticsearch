package com.lhl.annotatioin;

import java.lang.annotation.*;

/**
 * 分页开始.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageFrom {
}
