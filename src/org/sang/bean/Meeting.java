package org.sang.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Meeting {
    private int meetingid;
    private String meetingname;
    private int roomid;
    private int reservationistid;
    private int numberofparticipants;
    private Date orderdate;
    private int starttimeid;
    private String starttime;
    private int durationid;
    private String duration;
    private Timestamp reservationtime;
    private Timestamp canceledtime;
    private String description;
    private int status;
    private int deductcredits;
    private String roomname;
    private String empname;
    private String canceledreason;
    private String deductreason;

    public String getCanceledreason() {
        return canceledreason;
    }

    public void setCanceledreason(String canceledreason) {
        this.canceledreason = canceledreason;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Meeting(String meetingname, int roomid, int reservationistid, int numberofparticipants, Date orderdate, int starttimeid, int durationid, Timestamp reservationtime, String description) {
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.reservationistid = reservationistid;
        this.numberofparticipants = numberofparticipants;
        this.orderdate = orderdate;
        this.starttimeid = starttimeid;
        this.durationid = durationid;
        this.reservationtime = reservationtime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingid=" + meetingid +
                ", meetingname='" + meetingname + '\'' +
                ", roomid=" + roomid +
                ", reservationistid=" + reservationistid +
                ", numberofparticipants=" + numberofparticipants +
                ", orderdate=" + orderdate +
                ", starttimeid=" + starttimeid +
                ", durationid=" + durationid +
                ", reservationtime=" + reservationtime +
                ", canceledtime=" + canceledtime +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", roomname='" + roomname + '\'' +
                ", empname='" + empname + '\'' +
                ", deductcredits='" + deductcredits + '\'' +
                '}';
    }

    public Meeting(int meetingid, String meetingname, int roomid, int reservationistid, int numberofparticipants, Date orderdate, int starttimeid, String starttime, int durationid, String duration, Timestamp reservationtime, Timestamp canceledtime, String description, int status) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.reservationistid = reservationistid;
        this.numberofparticipants = numberofparticipants;
        this.orderdate = orderdate;
        this.starttimeid = starttimeid;
        this.starttime = starttime;
        this.durationid = durationid;
        this.duration = duration;
        this.reservationtime = reservationtime;
        this.canceledtime = canceledtime;
        this.description = description;
        this.status = status;
    }

    public Meeting(int meetingid, String meetingname, int roomid, int reservationistid, int numberofparticipants, Date orderdate, int starttimeid, String starttime, int durationid, String duration, Timestamp reservationtime, Timestamp canceledtime, String description, int status, int deductcredits) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.reservationistid = reservationistid;
        this.numberofparticipants = numberofparticipants;
        this.orderdate = orderdate;
        this.starttimeid = starttimeid;
        this.starttime = starttime;
        this.durationid = durationid;
        this.duration = duration;
        this.reservationtime = reservationtime;
        this.canceledtime = canceledtime;
        this.description = description;
        this.status = status;
        this.deductcredits = deductcredits;
    }

    //获取详细信息
    public Meeting(int meetingid, String meetingname, int roomid, int reservationistid, String empname, int numberofparticipants, Date orderdate, int starttimeid, String starttime, int durationid, String duration, Timestamp reservationtime, Timestamp canceledtime, String description, int status, String canceledreason, int deductcredits, String deductreason) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.reservationistid = reservationistid;
        this.empname = empname;
        this.numberofparticipants = numberofparticipants;
        this.orderdate = orderdate;
        this.starttimeid = starttimeid;
        this.starttime = starttime;
        this.durationid = durationid;
        this.duration = duration;
        this.reservationtime = reservationtime;
        this.canceledtime = canceledtime;
        this.description = description;
        this.status = status;
        this.canceledreason = canceledreason;
        this.deductcredits = deductcredits;
        this.deductreason = deductreason;
    }

    public int getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(int meetingid) {
        this.meetingid = meetingid;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getReservationistid() {
        return reservationistid;
    }

    public void setReservationistid(int reservationistid) {
        this.reservationistid = reservationistid;
    }

    public int getNumberofparticipants() {
        return numberofparticipants;
    }

    public void setNumberofparticipants(int numberofparticipants) {
        this.numberofparticipants = numberofparticipants;
    }

    public int getStarttimeId() {
        return starttimeid;
    }

    public void setstarttimeId(int starttimeid) {
        this.starttimeid = starttimeid;
    }

    public int getDurationId() {
        return durationid;
    }

    public void setdurationid(int durationid) {
        this.durationid = durationid;
    }

    public Timestamp getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(Timestamp reservationtime) {
        this.reservationtime = reservationtime;
    }

    public Timestamp getCanceledtime() {
        return canceledtime;
    }

    public void setCanceledtime(Timestamp canceledtime) {
        this.canceledtime = canceledtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeductcredits() {
        return deductcredits;
    }

    public void setDeductcredits(int deductcredits) {
        this.deductcredits = deductcredits;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getDuration() {
        return duration;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStarttimeid() {
        return starttimeid;
    }

    public void setStarttimeid(int starttimeid) {
        this.starttimeid = starttimeid;
    }

    public int getDurationid() {
        return durationid;
    }

    public void setDurationid(int durationid) {
        this.durationid = durationid;
    }

    public String getDeductreason() {
        return deductreason;
    }

    public void setDeductreason(String deductreason) {
        this.deductreason = deductreason;
    }
}
