package org.sang.dao;

import org.sang.bean.MeetingRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingRoomDao {
    public List<MeetingRoom> getAllMeetingRoom() {
        List<MeetingRoom> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from meetingroom;");
            rs = ps.executeQuery();
            while (rs.next()) {
                MeetingRoom meetingRoom = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("limits"), rs.getInt("status"), rs.getString("description"));
                list.add(meetingRoom);
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

    public List<MeetingRoom> getAllUseableMeetingRoom(int limits) {
        List<MeetingRoom> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("select * from meetingroom where `status` = 0");
        if (limits == 1) {
            sb.append(" and `limits` = 0");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                MeetingRoom meetingRoom = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("limits"), rs.getInt("status"), rs.getString("description"));
                list.add(meetingRoom);
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

    public MeetingRoom getMeetingRoomById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from meetingroom WHERE roomid=?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                MeetingRoom meetingRoom = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("limits"), rs.getInt("status"), rs.getString("description"));
                return meetingRoom;
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

    public int insert(MeetingRoom meetingRoom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("INSERT INTO meetingroom(roomnum,roomname,capacity,limits,status,description) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, meetingRoom.getRoomnum());
            ps.setString(2, meetingRoom.getRoomname());
            ps.setInt(3, meetingRoom.getCapacity());
            ps.setInt(4, meetingRoom.getLimits());
            ps.setInt(5, meetingRoom.getStatus());
            ps.setString(6, meetingRoom.getDescription());
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

    public int update(MeetingRoom meetingRoom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE meetingroom set roomnum=?,roomname=?,capacity=?,limits=?,status=?,description=? WHERE roomid=?");
            ps.setInt(1, meetingRoom.getRoomnum());
            ps.setString(2, meetingRoom.getRoomname());
            ps.setInt(3, meetingRoom.getCapacity());
            ps.setInt(4, meetingRoom.getLimits());
            ps.setInt(5, meetingRoom.getStatus());
            ps.setString(6, meetingRoom.getDescription());
            ps.setInt(7, meetingRoom.getRoomid());
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

    public static void main(String[] args) {
        MeetingRoom meetingRoomById = new MeetingRoomDao().getMeetingRoomById(3);
        System.out.println(meetingRoomById);
    }

}
