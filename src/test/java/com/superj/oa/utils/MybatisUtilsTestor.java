package com.superj.oa.utils;

import junit.framework.TestCase;
import org.junit.Test;

public class MybatisUtilsTestor extends TestCase {

    @Test
    public void testcase1(){
        String result = (String)MybatisUtils.executeQuery(sqlSession -> {
            String out = (String)sqlSession.selectOne("test.sample");
            return out;
        });
        System.out.println(result);
    }
    @Test
    public void testcase2(){
        String result = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(result);
    }
}