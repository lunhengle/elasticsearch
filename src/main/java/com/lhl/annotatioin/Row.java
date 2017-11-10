package com.lhl.annotatioin;

import com.lhl.constants.Condition;
import com.lhl.constants.Match;

import java.lang.annotation.*;

/**
 * Row 查询条件注解.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Row {
    /**
     * 字段名称.
     *
     * @return 字段名称
     */
    String name();

    /**
     * 条件运算.
     * must 且关系
     * must_not 非关系
     * should 或关系
     * gt 大于
     * lt 小于
     * eq 等于
     * gt_eq 大于等于
     * lt_eq 小于等于
     * 默认 且关系
     *
     * @return 条件运算
     */
    Condition condition() default Condition.MUST;

    /**
     * 匹配类型.
     * match 全文匹配
     * match_phrase 短语匹配
     * term 完全匹配
     * 默认短语匹配
     *
     * @return 匹配类型
     */
    Match match() default Match.MATCH_PHRASE;

}
