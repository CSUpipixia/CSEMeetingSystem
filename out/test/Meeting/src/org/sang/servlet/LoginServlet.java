package org.sang.servlet;

import org.sang.service.EmployeeService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accountname = req.getParameter("accountname");
        String password = req.getParameter("password");

        int login = employeeService.login(accountname, password);
        ServletContext sc = req.getServletContext();
        Object visitcount = sc.getAttribute("vc");
        if (visitcount == null) {
            visitcount = "0";
        }
        int vc = Integer.parseInt(visitcount.toString()) + 1;
        sc.setAttribute("vc", vc);

        if (login == 1) {
            req.getSession().setAttribute("loginUser", employeeService.getLoginUser());
            resp.sendRedirect(req.getContextPath() + "/main");
        } else if (login == 0) {
            req.setAttribute("loginError", "用户名或者密码输入错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else if (login == -1) {
            req.setAttribute("loginError", "账号已关闭，登陆失败，请联系管理员");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
