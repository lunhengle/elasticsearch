package com.lhl.handle;

import com.lhl.constants.Condition;
import com.lhl.constants.Match;

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
    private Condition condition;
    /**
     * 匹配类型.
     */
    private Match match;

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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
