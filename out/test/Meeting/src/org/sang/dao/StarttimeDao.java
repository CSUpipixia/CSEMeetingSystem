package org.sang.dao;

import org.sang.bean.Starttime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StarttimeDao {
    public List<Starttime> getAllStarttime() {
        List<Starttime> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from starttime;");
            rs = ps.executeQuery();
            while (rs.next()) {
                Starttime starttime = new Starttime(rs.getInt("starttimeid"), rs.getString("starttime"));
                list.add(starttime);
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
        System.out.println(new StarttimeDao().getAllStarttime());
    }
}
