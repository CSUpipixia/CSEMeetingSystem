package org.sang.dao;

import org.sang.bean.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DurationDao {
    public List<Duration> getAllDuration() {
        List<Duration> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from duration;");
            rs = ps.executeQuery();
            while (rs.next()) {
                Duration duration = new Duration(rs.getInt("durationid"), rs.getString("duration"));
                list.add(duration);
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

    public static void main(String[] args) {
        System.out.println(new DurationDao().getAllDuration());
    }
}
