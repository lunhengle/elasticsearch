package com.lhl.util;

import com.lhl.constants.Sort;
import com.lhl.constants.SortInfo;
import org.junit.Test;

/**
 * 测试排序.
 */
public class TestSortInfo {
    @Test
    public void testSortInfo() {
        System.out.println(SortInfo.getValue(Sort.DESC));
    }
}
