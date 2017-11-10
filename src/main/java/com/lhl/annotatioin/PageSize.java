package com.lhl.annotatioin;

import java.lang.annotation.*;

/**
 * 分页大小.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageSize {
}
