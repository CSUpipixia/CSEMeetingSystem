package org.sang.servlet;

import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class DoCancelMeetingServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String type = req.getParameter("type");
        String canceledreason = URLDecoder.decode(req.getParameter("canceledreason"),"UTF-8");

        if ("admin".equals(type)) {
            // 管理员取消状态为2
            if (meetingService.cancelMeeting(Integer.parseInt(mid), 2, canceledreason) == 1) {
                resp.sendRedirect(req.getContextPath() + "/searchmeeting");
            }
        } else {
            // 借用人取消状态为1
            if (meetingService.cancelMeeting(Integer.parseInt(mid), 1, canceledreason) == 1) {
                resp.sendRedirect(req.getContextPath() + "/mybooking");
            }
        }
    }
}
