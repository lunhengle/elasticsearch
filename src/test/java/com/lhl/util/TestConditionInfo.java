package com.lhl.util;

import com.lhl.constants.ConditionInfo;
import com.lhl.constants.Condition;
import org.junit.Test;

/**
 * 测试 条件运算 枚举类.
 */
public class TestConditionInfo {
    @Test
    public void testValue() {
        System.out.println(ConditionInfo.MUST);
    }

    @Test
    public void testKeyValue() {
        System.out.println(ConditionInfo.getValue(Condition.MUST));
    }
}
