package com.lhl.constants;

/**
 * 字段匹配常量.
 * must 且关系
 * must_not 非关系
 * should 或关系
 */
public enum Condition {
    /**
     * 且关系
     */
    MUST,
    /**
     * 非关系
     */
    MUST_NOT,
    /**
     * 或关系
     */
    SHOULD
}
