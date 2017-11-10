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
public enum Match {
    /**
     * 全文匹配.
     */
    MATCH(MatchRow.MATCH, "match"),
    /**
     * 短语匹配.
     */
    MATCH_PHRASE(MatchRow.MATCH_PHRASE, "match_phrase"),
    /**
     * 完全匹配.
     */
    TERM(MatchRow.TERM, "term"),
    /**
     * 大于.
     */
    GT(MatchRow.GT, "gt"),
    /**
     * 小于.
     */
    LT(MatchRow.LT, "lt"),
    /**
     * 等于.
     */
    EQ(MatchRow.EQ, "eq"),
    /**
     * 大于等于.
     */
    GTE(MatchRow.GTE, "gte"),
    /**
     * 小于等于.
     */
    LTE(MatchRow.LTE, "lte");

    /**
     * key.
     */
    private MatchRow key;
    /**
     * value.
     */
    private String value;

    /**
     * 构造函数.
     *
     * @param key   key
     * @param value value
     */
    Match(MatchRow key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(MatchRow key) {
        String value = "";
        for (Match match : Match.values()) {
            if (match.key.equals(key)) {
                value = match.value;
                break;
            }
        }
        return value;
    }
}
