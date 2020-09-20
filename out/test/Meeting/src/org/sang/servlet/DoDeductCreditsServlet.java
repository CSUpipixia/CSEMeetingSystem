package org.sang.servlet;

import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class DoDeductCreditsServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String deductreason = URLDecoder.decode(req.getParameter("deductreason"),"UTF-8");

        //扣除2分积分, 返回影响行数2
        if (meetingService.deductCreditsById(Integer.parseInt(mid), 2, deductreason) == 2) {
            resp.sendRedirect(req.getContextPath() + "/searchmeeting");
        } else {
            resp.sendError(400,"扣分失败");
        }
    }
}
