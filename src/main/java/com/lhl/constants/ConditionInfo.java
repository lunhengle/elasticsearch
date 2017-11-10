package com.lhl.constants;

/**
 * 条件类型.
 * must 且关系
 * must_not 非关系
 * should 或关系
 */
public enum ConditionInfo {
    /**
     * 且关系.
     */
    MUST(Condition.MUST, "must"),
    /**
     * 非关系.
     */
    MUST_NOT(Condition.MUST_NOT, "must_not"),
    /**
     * 或关系.
     */
    SHOULD(Condition.SHOULD, "should");
    /**
     * key.
     */
    private Condition key;
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
    ConditionInfo(Condition key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(Condition key) {
        String value = "";
        for (ConditionInfo conditionInfo : ConditionInfo.values()) {
            if (conditionInfo.key.equals(key)) {
                value = conditionInfo.value;
                break;
            }
        }
        return value;
    }

}
