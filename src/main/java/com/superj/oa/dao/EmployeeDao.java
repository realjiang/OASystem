package com.superj.oa.dao;


import com.superj.oa.entity.Employee;

public interface EmployeeDao {
    public Employee selectById(Long employeeId);
}
