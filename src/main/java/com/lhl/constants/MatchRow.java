package com.lhl.constants;

/**
 * 匹配类型.
 * match 全文匹配
 * match_phrase 短语匹配
 * term 完全匹配
 * gt 大于
 * lt 小于
 * eq 等于
 * gte 大于等于
 * lte 小于等于
 */
public enum MatchRow {
    /**
     * 全文匹配.
     */
    MATCH,
    /**
     * 短语匹配.
     */
    MATCH_PHRASE,
    /**
     * 完全匹配.
     */
    TERM,
    /**
     * 大于
     */
    GT,
    /**
     * 小于
     */
    LT,
    /**
     * 等于
     */
    EQ,
    /**
     * 大于等于
     */
    GTE,
    /**
     * 小于等于
     */
    LTE
}
