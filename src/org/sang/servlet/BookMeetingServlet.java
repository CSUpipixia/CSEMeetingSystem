package org.sang.servlet;

import org.sang.bean.Duration;
import org.sang.bean.Employee;
import org.sang.bean.MeetingRoom;
import org.sang.bean.Starttime;
import org.sang.service.MeetingRoomService;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookMeetingServlet extends HttpServlet {
    private MeetingRoomService meetingRoomService = new MeetingRoomService();
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object loginUser = req.getSession().getAttribute("loginUser");
        int loginEmpRole = ((Employee) loginUser).getRole();
        int limits = 0;
        if (loginEmpRole == 1 || loginEmpRole == 32) {
            limits = 1;
        }
        List<MeetingRoom> list = meetingRoomService.getAllUseableMeetingRoom(limits);
        List<Starttime> starttimes = meetingService.getStarttimes();
        List<Duration> durations = meetingService.getDurations();
        req.setAttribute("mrs", list);
        req.setAttribute("starts", starttimes);
        req.setAttribute("durations", durations);
        req.getRequestDispatcher("/bookmeeting.jsp").forward(req, resp);
    }
}
