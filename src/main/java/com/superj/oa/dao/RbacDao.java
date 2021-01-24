package com.superj.oa.dao;

import com.superj.oa.entity.Node;
import com.superj.oa.utils.MybatisUtils;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId){
        return (List) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId", userId));
    }
}
