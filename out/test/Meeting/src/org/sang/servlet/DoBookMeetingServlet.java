package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;
import org.sang.service.MeetingRoomService;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.Timestamp;

public class DoBookMeetingServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();
    private MeetingRoomService meetingRoomService = new MeetingRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingname = URLDecoder.decode(req.getParameter("meetingname"),"UTF-8");
        String numberofparticipants = req.getParameter("numberofparticipants");
        String orderdate = req.getParameter("orderdate");
        String starttimeid = req.getParameter("starttimeid");
        String durationid = req.getParameter("durationid");
        String roomid = req.getParameter("roomid");
        String replacereservationistid = req.getParameter("reservationistid");
        String description = URLDecoder.decode(req.getParameter("description"),"UTF-8");

        //获取当前登录的用户对象
        Employee loginUser = (Employee) req.getSession().getAttribute("loginUser");
        int reservationistid = loginUser.getEmployeeid();
        if (loginUser.getRole() == 16 && replacereservationistid != null && !"".equals(replacereservationistid)) {
            reservationistid = Integer.parseInt(replacereservationistid);
        }

        int totalCount = 0;

        if (orderdate != null && !"".equals(orderdate) && roomid != null && !"".equals(roomid) && starttimeid != null && !"".equals(starttimeid) && durationid != null && !"".equals(durationid))
        {
            totalCount = meetingService.checkRoom(Integer.parseInt(roomid), Date.valueOf(orderdate), Integer.parseInt(starttimeid), Integer.parseInt(durationid));
        }

        if (totalCount > 0)
        {

        } else {
            Meeting meeting = new Meeting(meetingname, Integer.parseInt(roomid), reservationistid, Integer.parseInt(numberofparticipants), Date.valueOf(orderdate), Integer.parseInt(starttimeid), Integer.parseInt(durationid), new Timestamp(System.currentTimeMillis()), description);
            meetingService.insert(meeting);
        }

        System.out.println(totalCount);
        PrintWriter out = resp.getWriter();
        out.write(String.valueOf(totalCount));
        out.close();
    }
}
