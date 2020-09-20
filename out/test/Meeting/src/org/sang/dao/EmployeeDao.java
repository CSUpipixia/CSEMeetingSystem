package org.sang.dao;

import org.sang.bean.Employee;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public int changePasswordById(int id, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE logininfo set password=? WHERE id=?");
            ps.setString(1, Encry(password));
            ps.setInt(2, id);
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

    public int updateEmpCreditsById(int id, int credits) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("UPDATE logininfo set credits=? WHERE id=?");
            ps.setInt(1, credits);
            ps.setInt(2, id);
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

    public List<Employee> getEmpByDepId(int depId) {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM logininfo WHERE departmentid1=?");
            ps.setInt(1,depId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"), rs.getString("realname"), rs.getString("loginname"), rs.getString("usertype"), rs.getString("locked"), rs.getInt("departmentid1"), Decry(rs.getString("password")), rs.getInt("usertype"), rs.getInt("credits")));
            }
            return list;
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

    public int getCount(String employeename, String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT count(*) FROM logininfo WHERE usertype > 0");
        if (employeename != null && !"".equals(employeename)) {
            sb.append(" and realname=?");
        }
        if (username != null && !"".equals(username)) {
            sb.append(" and loginname=?");
        }
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (employeename != null && !"".equals(employeename)) {
                ps.setString(index++, employeename);
            }
            if (username != null && !"".equals(username)) {
                ps.setString(index++, username);
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

    public List<Employee> searchEmp(String employeename, String username, int page, int count) {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer("SELECT * FROM logininfo WHERE usertype > 0");
        if (employeename != null && !"".equals(employeename)) {
            sb.append(" and realname=?");
        }
        if (username != null && !"".equals(username)) {
            sb.append(" and loginname=?");
        }
        sb.append(" limit ?,?");
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sb.toString());
            int index = 1;
            if (employeename != null && !"".equals(employeename)) {
                ps.setString(index++, employeename);
            }
            if (username != null && !"".equals(username)) {
                ps.setString(index++, username);
            }
            ps.setInt(index++, (page - 1) * count);
            ps.setInt(index++, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"), rs.getString("realname"), rs.getString("loginname"), rs.getString("usertype"), rs.getString("locked"), rs.getInt("departmentid1"), Decry(rs.getString("password")), rs.getInt("usertype"), rs.getInt("credits")));
            }
            return list;
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

    public Employee login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM logininfo WHERE loginname=? AND password=?");
            ps.setString(1, username);
            String encrypassword = Encry(password);
            ps.setString(2, encrypassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("realname"), rs.getString("loginname"), rs.getString("usertype"), rs.getString("locked"), rs.getInt("departmentid1"), Decry(rs.getString("password")), rs.getInt("usertype"), rs.getInt("credits"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 加密
    private static String Encry(String password){
//        if(password == null) return null;
//        //把字符串转为字节数组
//        byte[] b = password.getBytes();
//        //遍历
//        for(int i=0;i<b.length;i++) {
//            b[i] += i+1;//在原有的基础上i+1
//        }
//        return new String(b);
        return md5(password);
    }

    // 解密
    private static String Decry(String encode){
//        if(encode==null) return null;
//        //把字符串转为字节数组
//        byte[] b = encode.getBytes();
//        //遍历
//        for(int i=0;i<b.length;i++) {
//            b[i] -= i+1;//在原有的基础上-(i+1)
//        }
//        return new String(b);
        return encode;
    }

    /**
     * 将字符串 MD5 加密
     */
    public static String md5(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(md5("csu"));
    }
}
