package com.lhl.util;

import com.lhl.constants.Match;
import com.lhl.constants.MatchInfo;
import org.junit.Test;

/**
 * 测试匹配.
 */
public class TestMatchInfo {
    @Test
    public void testMatch() {
       System.out.println(MatchInfo.getValue(Match.MATCH));
    }
}
