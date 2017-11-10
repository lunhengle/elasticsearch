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
public enum MatchInfo {
    /**
     * 全文匹配.
     */
    MATCH(Match.MATCH, "match"),
    /**
     * 短语匹配.
     */
    MATCH_PHRASE(Match.MATCH_PHRASE, "match_phrase"),
    /**
     * 完全匹配.
     */
    TERM(Match.TERM, "term"),
    /**
     * 大于.
     */
    GT(Match.GT, "gt"),
    /**
     * 小于.
     */
    LT(Match.LT, "lt"),
    /**
     * 等于.
     */
    EQ(Match.EQ, "eq"),
    /**
     * 大于等于.
     */
    GTE(Match.GTE, "gte"),
    /**
     * 小于等于.
     */
    LTE(Match.LTE, "lte");

    /**
     * key.
     */
    private Match key;
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
    MatchInfo(Match key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(Match key) {
        String value = "";
        for (MatchInfo matchInfo : MatchInfo.values()) {
            if (matchInfo.key.equals(key)) {
                value = matchInfo.value;
                break;
            }
        }
        return value;
    }
}
