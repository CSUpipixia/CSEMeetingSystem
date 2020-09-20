package org.sang.bean;

public class Starttime {
    int id;
    String starttime;

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "Starttime{" +
                "id=" + id +
                ", starttime='" + starttime + '\'' +
                '}';
    }

    public Starttime(int id, String starttime) {
        this.id = id;
        this.starttime = starttime;
    }

    public String getStarttime() {
        return starttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
