package org.sang.service;

import org.sang.bean.*;
import org.sang.dao.*;

import java.sql.Date;
import java.util.List;

public class MeetingService {
    private MeetingDao meetingDao = new MeetingDao();
    private MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
    private EmployeeDao employeeDao = new EmployeeDao();
    private StarttimeDao starttimeDao = new StarttimeDao();
    private DurationDao durationDao = new DurationDao();
    private List<Employee> emps;

    public List<Starttime> getStarttimes() {
        return starttimeDao.getAllStarttime();
    }

    public List<Duration> getDurations() {
        return durationDao.getAllDuration();
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void insert(Meeting meeting) {
        meetingDao.insert(meeting);
    }

    public List<Meeting> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int meetingfromtime, int meetingtotime, int page, int count) {
        return meetingDao.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, meetingfromtime, meetingtotime, page, count);
    }

    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
        return meetingDao.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
    }

    public int checkRoom(int roomid, Date orderdate, int starttimeid, int durationid) {
        return meetingDao.checkRoom(roomid, orderdate, starttimeid, durationid);
    }

    public Meeting getMeetingDetailsByMeetingId(int mid) {
        Meeting meeting = meetingDao.getMeetingById(mid);
        return meeting;
    }
    public List<Meeting> getCanceledMeeting(int empid) {
        return meetingDao.getCanceledMeeting(empid);
    }
//    public List<Meeting> getMeeting7Days(int empid) {
//        return meetingDao.getMeeting7Days(empid);
//    }
    public List<Meeting> getMyBookingMeeting(int empid) {
        return meetingDao.getMyBookingMeeting(empid);
    }
    public int cancelMeeting(int mid, int status, String reason) {
        return meetingDao.cancelMeeting(mid, status, reason);
    }
    public List<Meeting> getMyMeeting(int empid) {
        return meetingDao.getMyMeeting(empid);
    }

    public int deductCreditsById(int mid, int deductcredit, String deductreason) {
        return meetingDao.deductCreditsById(mid, deductcredit, deductreason);
    }

    public List getAllBookingByDate(String orderdate) {
        return meetingDao.getAllBookingInfoByDate(orderdate);
    }
}
