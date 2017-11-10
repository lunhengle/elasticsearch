package com.lhl.util;

import com.lhl.constants.MatchRow;
import com.lhl.constants.Match;
import org.junit.Test;

/**
 * 测试匹配.
 */
public class TestMatch {
    @Test
    public void testMatch() {
       System.out.println(Match.getValue(MatchRow.MATCH));
    }
}
