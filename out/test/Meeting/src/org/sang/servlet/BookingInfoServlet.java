package org.sang.servlet;

import org.sang.bean.Starttime;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookingInfoServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Starttime> starttimes = meetingService.getStarttimes();
        req.setAttribute("starts", starttimes);
        req.getRequestDispatcher("/bookinginfo.jsp").forward(req, resp);
    }
}
