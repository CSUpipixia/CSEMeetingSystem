package org.sang.servlet;

import org.sang.service.MeetingRoomService;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

public class CheckRoomServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();
    private MeetingRoomService meetingRoomService = new MeetingRoomService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderdate = req.getParameter("orderdate");
        String starttimeid = req.getParameter("starttimeid");
        String durationid = req.getParameter("durationid");
        String roomid = req.getParameter("roomid");

        int totalCount = 0;

        if (orderdate != null && !"".equals(orderdate) && roomid != null && !"".equals(roomid) && starttimeid != null && !"".equals(starttimeid) && durationid != null && !"".equals(durationid))
        {
            totalCount = meetingService.checkRoom(Integer.parseInt(roomid), Date.valueOf(orderdate), Integer.parseInt(starttimeid), Integer.parseInt(durationid));
        }

        PrintWriter out = resp.getWriter();
        out.write(String.valueOf(totalCount));
        out.close();
    }
}
