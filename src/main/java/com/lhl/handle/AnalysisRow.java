package com.lhl.handle;

import com.lhl.constants.ConditionRow;
import com.lhl.constants.MatchRow;

import java.io.Serializable;

/**
 * es row bean.
 */
public final class AnalysisRow implements Serializable {
    /**
     * 名称.
     */
    private String name;
    /**
     * 运算类型.
     */
    private ConditionRow condition;
    /**
     * 匹配类型.
     */
    private MatchRow match;

    /**
     * 值.
     */
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConditionRow getCondition() {
        return condition;
    }

    public void setCondition(ConditionRow condition) {
        this.condition = condition;
    }

    public MatchRow getMatch() {
        return match;
    }

    public void setMatch(MatchRow match) {
        this.match = match;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
