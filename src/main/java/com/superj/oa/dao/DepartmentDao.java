package com.superj.oa.dao;


import com.superj.oa.entity.Department;

public interface DepartmentDao {
    public Department selectById(Long departmentId);
}
