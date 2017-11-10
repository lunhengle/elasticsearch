package com.lhl.util;

import com.lhl.constants.Condition;
import com.lhl.constants.ConditionRow;
import org.junit.Test;

/**
 * 测试 条件运算 枚举类.
 */
public class TestCondition {
    @Test
    public void testValue() {
        System.out.println(Condition.MUST);
    }

    @Test
    public void testKeyValue() {
        System.out.println(Condition.getValue(ConditionRow.MUST));
    }
}
