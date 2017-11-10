package com.lhl.constants;

/**
 * 条件类型.
 * must 且关系
 * must_not 非关系
 * should 或关系
 */
public enum Condition {
    /**
     * 且关系.
     */
    MUST(ConditionRow.MUST, "must"),
    /**
     * 非关系.
     */
    MUST_NOT(ConditionRow.MUST_NOT, "must_not"),
    /**
     * 或关系.
     */
    SHOULD(ConditionRow.SHOULD, "should");
    /**
     * key.
     */
    private ConditionRow key;
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
    Condition(ConditionRow key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(ConditionRow key) {
        String value = "";
        for (Condition condition : Condition.values()) {
            if (condition.key.equals(key)) {
                value = condition.value;
                break;
            }
        }
        return value;
    }

}
