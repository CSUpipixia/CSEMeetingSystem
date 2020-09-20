package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeename = req.getParameter("employeename");
        String username = req.getParameter("username");
        String page = req.getParameter("page");
        String count = req.getParameter("count");

        if (page == null || "".equals(page)) {
            page = "1";
        }
        if (count == null || "".equals(count)) {
            count = "10";
        }
        List<Employee> list = employeeService.searchEmp(employeename, username, Integer.parseInt(page), Integer.parseInt(count));
        int totalCount = employeeService.getCount(employeename, username);
        int totalPage = totalCount / Integer.parseInt(count) + 1;
        req.setAttribute("list", list);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("employeename", employeename);
        req.setAttribute("username", username);
        req.getRequestDispatcher("/searchemployees.jsp").forward(req, resp);
    }
}
