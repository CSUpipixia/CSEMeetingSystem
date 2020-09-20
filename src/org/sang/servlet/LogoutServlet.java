package org.sang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //业务逻辑
        Enumeration em = req.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            req.getSession().removeAttribute(em.nextElement().toString());
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
