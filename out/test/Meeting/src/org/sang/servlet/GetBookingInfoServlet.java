package org.sang.servlet;

import com.google.gson.Gson;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetBookingInfoServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderdate = req.getParameter("orderdate");

        List bookings = meetingService.getAllBookingByDate(orderdate);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(new Gson().toJson(bookings));
    }
}
