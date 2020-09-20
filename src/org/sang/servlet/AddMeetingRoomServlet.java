package org.sang.servlet;

import org.sang.bean.MeetingRoom;
import org.sang.service.MeetingRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMeetingRoomServlet extends HttpServlet {
    private MeetingRoomService meetingRoomService = new MeetingRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomid = req.getParameter("roomid");
        String roomnum = req.getParameter("roomnum");
        String roomname = req.getParameter("roomname");
        String capacity = req.getParameter("capacity");
        String limits = req.getParameter("limits");
        String status = req.getParameter("status");
        String description = req.getParameter("description");
        MeetingRoom meetingRoom = new MeetingRoom(Integer.parseInt(roomnum), roomname, Integer.parseInt(capacity), Integer.parseInt(limits), Integer.parseInt(status), description);
        if (roomid == null || "".equals(roomid)) {
            //添加会议室
            int result = meetingRoomService.insert(meetingRoom);
            if (result == 1) {
                //添加成功
            } else {
                req.setAttribute("error", "添加失败");
            }
        } else {
            //修改会议室
            meetingRoom.setRoomid(Integer.parseInt(roomid));
            int update = meetingRoomService.update(meetingRoom);
            if (update == 1) {
                //修改成功
            }else{
                resp.sendError(400,"更新错误");
            }
        }
    }
}
