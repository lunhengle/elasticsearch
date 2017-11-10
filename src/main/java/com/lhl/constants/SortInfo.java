package com.lhl.constants;

/**
 * 排序.
 * desc 倒序排列
 * asc 正序排列
 */
public enum SortInfo {
    /**
     * 倒序排列.
     */
    DESC(Sort.DESC, "desc"),
    /**
     * 正序排列.
     */
    ASC(Sort.ASC, "asc");
    /**
     * key.
     */
    private Sort key;
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
    SortInfo(Sort key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(Sort key) {
        String value = "";
        for (SortInfo sortInfo : SortInfo.values()) {
            if (sortInfo.key.equals(key)) {
                value = sortInfo.value;
            }
        }
        return value;
    }
}
