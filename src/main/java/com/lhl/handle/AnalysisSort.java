package com.lhl.handle;

import com.lhl.constants.Sort;

import java.io.Serializable;

/**
 * 排序.
 */
final class AnalysisSort implements Serializable {
    /**
     * 排序字段名称.
     */
    private String name;
    /**
     * 排序字段得分.
     */
    private Integer score;
    /**
     * 排序.
     */
    private Sort sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
