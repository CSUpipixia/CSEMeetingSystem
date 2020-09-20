package org.sang.filter;

import org.sang.bean.Employee;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        if ("/rule.jsp".equals(servletPath) || "/login.jsp".equals(servletPath) ||"/main.jsp".equals(servletPath) || "/jss.jsp".equals(servletPath) || "/login".equals(servletPath) || servletPath.contains("/images/") || servletPath.contains("/js/") || servletPath.contains("/My97DatePicker/") || servletPath.contains("/styles/")) {
            chain.doFilter(req, response);
        } else {
            Object loginUser = req.getSession().getAttribute("loginUser");
            if (loginUser == null) {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                // 管理员权限
                if ("/resetcredits".equals(servletPath) || "/deductcredits".equals(servletPath) || "/addmr".equals(servletPath) || "/toaddmr".equals(servletPath) ||"/serachemp".equals(servletPath) ||"/searchmeeting".equals(servletPath)) {
                    int loginEmpRole = ((Employee) loginUser).getRole();
                    if (loginEmpRole != 16) {
                        ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
                    } else {
                        chain.doFilter(req, response);
                    }
                } else {
                    chain.doFilter(req, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
