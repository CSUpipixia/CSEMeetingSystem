package org.sang.dao;

import com.google.gson.Gson;
import org.sang.bean.Meeting;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingDao {
    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM meeting m,logininfo e,meetingroom mr WHERE m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`id`");
        if (meetingname != null && !"".equals(meetingname)) {
            sb.append(" and meetingname=?");
        }
        if (roomname != null && !"".equals(roomname)) {
            sb.append(" and roomname=?");
        }
        if (reservername != null && !"".equals(reservername)) {
            sb.append(" and realname=?");
        }
        if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
            sb.append(" and reservationtime > ? and reservationtime<?");
        }
        if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
            sb.append(" and starttimeid>? and durationid<?");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (meetingname != null && !"".equals(meetingname)) {
                ps.setString(index++, meetingname);
            }
            if (roomname != null && !"".equals(roomname)) {
                ps.setString(index++, roomname);
            }
            if (reservername != null && !"".equals(reservername)) {
                ps.setString(index++, reservername);
            }
            if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
            }
            if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(meetingfromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(meetingtodate));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public int checkRoom(int roomid, Date orderdate, int starttimeid, int durationid) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM meeting WHERE status=0 and roomid=? and (durationid+starttimeid > ? AND durationid+starttimeid <= ? or starttimeid >= ? AND starttimeid < ?)");
        if (orderdate != null && !"".equals(orderdate)) {
            sb.append(" and orderdate=?");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            ps.setInt(index++, roomid);
            ps.setInt(index++, starttimeid);
            ps.setInt(index++, durationid + starttimeid);
            ps.setInt(index++, starttimeid);
            ps.setInt(index++, durationid + starttimeid);
            if (orderdate != null && !"".equals(orderdate)) {
                ps.setDate(index++, orderdate);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public List<Meeting> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int meetingfromtime, int meetingtotime, int page, int count) {
        List<Meeting> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT m.*,s.*,d.*,e.`realname` AS employeename,mr.`roomname` AS roomname FROM meeting m,logininfo e,meetingroom mr,starttime s,duration d WHERE m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`id` AND m.`starttimeid`=s.`starttimeid` AND m.`durationid`=d.`durationid`");
        if (meetingname != null && !"".equals(meetingname)) {
            sb.append(" and meetingname=?");
        }
        if (roomname != null && !"".equals(roomname)) {
            sb.append(" and roomname=?");
        }
        if (reservername != null && !"".equals(reservername)) {
            sb.append(" and realname=?");
        }
        if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
            sb.append(" and reservationtime >= ? and reservationtime<=?");
        }
        if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
            sb.append(" and orderdate>=? and orderdate<=?");
        }
        if (meetingfromtime != 0 && !"".equals(meetingfromtime) && meetingtotime != 0 && !"".equals(meetingtotime)) {
            sb.append(" and m.starttimeid>=? and m.starttimeid+m.durationid<=?");
        }
        sb.append(" ORDER BY m.reservationtime DESC");
        sb.append(" limit ?,?");
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (meetingname != null && !"".equals(meetingname)) {
                ps.setString(index++, meetingname);
            }
            if (roomname != null && !"".equals(roomname)) {
                ps.setString(index++, roomname);
            }
            if (reservername != null && !"".equals(reservername)) {
                ps.setString(index++, reservername);
            }
            if (reservefromdate != null && !"".equals(reservefromdate) && reservetodate != null && !"".equals(reservetodate)) {
                ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
                ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
            }
            if (meetingfromdate != null && !"".equals(meetingfromdate) && meetingtodate != null && !"".equals(meetingtodate)) {
                ps.setDate(index++, Date.valueOf(meetingfromdate));
                ps.setDate(index++, Date.valueOf(meetingtodate));
            }
            if (meetingfromtime != 0 && !"".equals(meetingfromtime) && meetingtotime != 0 && !"".equals(meetingtotime)) {
                ps.setInt(index++, meetingfromtime);
                ps.setInt(index++, meetingtotime);
            }
            ps.setInt(index++, (page - 1) * count);
            ps.setInt(index++, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("meetingid");
                String meetingname1 = rs.getString("meetingname");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Date orderdate = rs.getDate("orderdate");
                int starttimeid = rs.getInt("starttimeid");
                String starttime = rs.getString("starttime");
                int durationid = rs.getInt("durationid");
                String duration = rs.getString("duration");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int deductcredits = rs.getInt("deductcredits");
                String employeename = rs.getString("employeename");
                String roomname1 = rs.getString("roomname");
                Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, orderdate, starttimeid, starttime, durationid, duration, reservationtime, canceledtime, description, status, deductcredits);
                meeting.setEmpname(employeename);
                meeting.setRoomname(roomname1);
                list.add(meeting);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    public List<Meeting> getMyBookingMeeting(int empid) {
        List<Meeting> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,s.*,d.*,mr.`roomname` AS roomname FROM meeting m,meetingroom mr,starttime s,duration d WHERE m.`reservationistid`=? AND m.`roomid`=mr.`roomid` AND m.`starttimeid`=s.`starttimeid` AND m.`durationid`=d.`durationid` ORDER BY m.reservationtime DESC");
            ps.setInt(1, empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("meetingid");
                String meetingname1 = rs.getString("meetingname");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Date orderdate = rs.getDate("orderdate");
                int starttimeid = rs.getInt("starttimeid");
                String starttime = rs.getString("starttime");
                int durationid = rs.getInt("durationid");
                String duration = rs.getString("duration");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                int deductcredits = rs.getInt("deductcredits");
                Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, orderdate, starttimeid, starttime, durationid, duration, reservationtime, canceledtime, description, status, deductcredits);
                meeting.setRoomname(roomname1);
                list.add(meeting);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    public List<Meeting> getCanceledMeeting(int empid) {
        List<Meeting> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,s.*,d.*,mr.`roomname` AS roomname FROM meeting m,meetingroom mr,starttime s,duration d WHERE m.reservationistid=? AND m.`roomid`=mr.`roomid` AND m.status=1 AND m.`starttimeid`=s.`starttimeid` AND m.`durationid`=d.`durationid`");
            ps.setInt(1, empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("meetingid");
                String meetingname1 = rs.getString("meetingname");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Date orderdate = rs.getDate("orderdate");
                int starttimeid = rs.getInt("starttimeid");
                String starttime = rs.getString("starttime");
                int durationid = rs.getInt("durationid");
                String duration = rs.getString("duration");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                String canceledreason = rs.getString("canceledreason");
                Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, orderdate, starttimeid, starttime, durationid, duration, reservationtime, canceledtime, description, status);
                meeting.setCanceledreason(canceledreason);
                meeting.setRoomname(roomname1);
                list.add(meeting);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    public List<Meeting> getMyMeeting(int empid) {
        List<Meeting> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT m.*,s.*,d.*,e.`realname`,mr.`roomname` FROM meeting m,logininfo e,meetingroom mr,starttime s,duration d WHERE m.`reservationistid`=? AND m.`roomid`=mr.`roomid` AND m.`reservationistid`=e.`employeeid` and m.status=0 AND m.starttimeid>? AND m.`starttimeid`=s.`starttimeid` AND m.`durationid`=d.`durationid`");
            ps.setInt(1, empid);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            rs = ps.executeQuery();
            while (rs.next()) {
                int meetingid = rs.getInt("meetingid");
                String meetingname1 = rs.getString("meetingname");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Date orderdate = rs.getDate("orderdate");
                int starttimeid = rs.getInt("starttimeid");
                String starttime = rs.getString("starttime");
                int durationid = rs.getInt("durationid");
                String duration = rs.getString("duration");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String roomname1 = rs.getString("roomname");
                String employeename = rs.getString("realname");
                String canceledreason = rs.getString("canceledreason");
                int deductcredits = rs.getInt("deductcredits");
                Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, orderdate, starttimeid, starttime, durationid, duration, reservationtime, canceledtime, description, status, deductcredits);
                meeting.setCanceledreason(canceledreason);
                meeting.setRoomname(roomname1);
                meeting.setEmpname(employeename);
                list.add(meeting);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    /**
     * 添加一条会议记录
     *
     * @param meeting
     * @return 返回值为插入记录的id
     */
    public int insert(Meeting meeting) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into meeting (meetingname,roomid,reservationistid,numberofparticipants,orderdate,starttimeid,durationid,reservationtime,description,status,deductcredits) values (?,?,?,?,?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, meeting.getMeetingname());
            ps.setInt(2, meeting.getRoomid());
            ps.setInt(3, meeting.getReservationistid());
            ps.setInt(4, meeting.getNumberofparticipants());
            ps.setDate(5, meeting.getOrderdate());
            ps.setInt(6, meeting.getStarttimeId());
            ps.setInt(7, meeting.getDurationId());
            ps.setTimestamp(8, meeting.getReservationtime());
            ps.setString(9, meeting.getDescription());
            ps.setInt(10, meeting.getStatus());
            ps.setInt(11, 0);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public Meeting getMeetingById(int mid) {
        Meeting meeting = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from meeting m, logininfo e,starttime s,duration d WHERE m.meetingid=? and m.reservationistid = e.id AND m.`starttimeid`=s.`starttimeid` AND m.`durationid`=d.`durationid`;");
            ps.setInt(1, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                int meetingid = rs.getInt("meetingid");
                String meetingname1 = rs.getString("meetingname");
                int roomid = rs.getInt("roomid");
                int reservationistid = rs.getInt("reservationistid");
                String empname = rs.getString("realname");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Date orderdate = rs.getDate("orderdate");
                int starttimeid = rs.getInt("starttimeid");
                String starttime = rs.getString("starttime");
                int durationid = rs.getInt("durationid");
                String duration = rs.getString("duration");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                String canceledreason = rs.getString("canceledreason");
                int deductcredits = rs.getInt("deductcredits");
                String deductreason = rs.getString("deductreason");
                meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, empname, numberofparticipants, orderdate, starttimeid, starttime, durationid, duration, reservationtime, canceledtime, description, status, canceledreason, deductcredits, deductreason);
                return meeting;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return null;
    }

    public int cancelMeeting(int mid, int status, String reason) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE meeting SET status=?,canceledtime=?,canceledreason=? WHERE meetingid=?");
            ps.setInt(1, status);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setString(3, reason);
            ps.setInt(4, mid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public int deductCreditsById(int mid, int deductcredit, String deductreason) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE logininfo e, meeting m set e.credits=e.credits-?, m.deductcredits=?, m.deductreason=? WHERE m.meetingid=? and e.id=m.reservationistid");
            ps.setInt(1, deductcredit);
            ps.setInt(2, deductcredit);
            ps.setString(3, deductreason);
            ps.setInt(4, mid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }

    public List getAllBookingInfoByDate(String orderdate) {
        List list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select distinct mr.roomid,mr.roomname,m.starttimeid,m.durationid,m.realname,m.orderdate from meetingroom as mr left join (select * from meeting,logininfo where meeting.reservationistid = logininfo.id) as m on m.roomid=mr.roomid and m.orderdate =? and m.`status` = 0 order by mr.roomid,m.starttimeid");
            ps.setDate(1, Date.valueOf(orderdate));
            rs = ps.executeQuery();
            while (rs.next()) {
                int roomid = rs.getInt("roomid");
                Map rowData = new HashMap();
                rowData.put("roomid", roomid);
                rowData.put("roomname", rs.getString("roomname"));
                rowData.put("realname", rs.getString("realname"));
                rowData.put("starttimeid", rs.getInt("starttimeid"));
                rowData.put("durationid", rs.getInt("durationid"));
                list.add(rowData);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return null;
    }

    public static void main(String[] args) {
        List list = new MeetingDao().getAllBookingInfoByDate("2019-10-07");
        System.out.println(list.toString());
        System.out.println(new Gson().toJson(list));
    }
}
