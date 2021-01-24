package com.superj.oa.controller;


import com.superj.oa.entity.Department;
import com.superj.oa.entity.Employee;
import com.superj.oa.entity.Node;
import com.superj.oa.entity.User;
import com.superj.oa.service.DepartmentService;
import com.superj.oa.service.EmployeeService;
import com.superj.oa.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private UserService userService = new UserService();
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //得到当前登录用户对象
        User user = (User)session.getAttribute("login_user");
        //获取当前登录的员工对象
        Employee employee = employeeService.selectById(user.getEmployeeId());
        //获取员工对应的部门
        Department department = departmentService.selectById(employee.getDepartmentId());
        //获取登录用户可用功能模块列表
        List<Node> nodeList = userService.selectNodeByUserId(user.getUserId());
        //放入请求属性
        request.setAttribute("node_list" , nodeList);
            //除了index.ftl 其他地方也可能会反复使用，因此放大范围，存至session而不是request中
        session.setAttribute("current_employee" , employee);
        session.setAttribute("current_department", department);
        //请求派发至ftl进行展现
        request.getRequestDispatcher("/index.ftl").forward(request,response);
    }
}
