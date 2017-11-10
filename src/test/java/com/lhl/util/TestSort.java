package com.lhl.util;

import com.lhl.constants.SortRow;
import com.lhl.constants.Sort;
import org.junit.Test;

/**
 * 测试排序.
 */
public class TestSort {
    @Test
    public void testSort() {
        System.out.println(Sort.getValue(SortRow.DESC));
    }
}
