package com.lhl.constants;

/**
 * 排序.
 * desc 倒序排列
 * asc 正序排列
 */
public enum Sort {
    /**
     * 倒序排列.
     */
    DESC(SortRow.DESC, "desc"),
    /**
     * 正序排列.
     */
    ASC(SortRow.ASC, "asc");
    /**
     * key.
     */
    private SortRow key;
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
    Sort(SortRow key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key 获取value.
     *
     * @param key key
     * @return value
     */
    public static String getValue(SortRow key) {
        String value = "";
        for (Sort sort : Sort.values()) {
            if (sort.key.equals(key)) {
                value = sort.value;
            }
        }
        return value;
    }
}
