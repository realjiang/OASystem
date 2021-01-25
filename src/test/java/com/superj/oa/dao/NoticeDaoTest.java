package com.superj.oa.dao;

import com.superj.oa.entity.Notice;
import com.superj.oa.utils.MybatisUtils;
import org.junit.Test;

import java.util.Date;

public class NoticeDaoTest {
    @Test
    public void testInsert(){
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setReceiverId(2l);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            dao.insert(notice);
            return null;
        });
    }

}