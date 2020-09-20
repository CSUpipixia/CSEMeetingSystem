package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoChangePasswordServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loginEmpId = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        String loginEmpUsername = ((Employee) req.getSession().getAttribute("loginUser")).getUsername();
        String loginEmpPassword = ((Employee) req.getSession().getAttribute("loginUser")).getPassword();

        String originpassword = req.getParameter("origin");
        String newpassword = req.getParameter("new");
        String confirmpassword = req.getParameter("confirm");

        if (!loginEmpPassword.equals(originpassword))
        {
            resp.sendError(400,"原密码输入错误，请重新输入");
        } else if (newpassword.length() == 0) {
            resp.sendError(401,"新密码不能为空");
        } else if (!newpassword.equals(confirmpassword)) {
            resp.sendError(402,"两次密码输入不一致，请重新输入");
        } else {
            int change = employeeService.changePasswordById(loginEmpId, newpassword);
            if (change == -1) {
                resp.sendError(500,"密码更新失败");
            } else if (change == 1) {
                if (employeeService.login(loginEmpUsername, newpassword) == 1)
                    req.getSession().setAttribute("loginUser", employeeService.getLoginUser());
            }
        }
    }
}
