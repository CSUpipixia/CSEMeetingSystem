package org.sang.bean;

public class Duration {
    int durationid;
    String duration;

    @Override
    public String toString() {
        return "Duration{" +
                "durationid=" + durationid +
                ", duration='" + duration + '\'' +
                '}';
    }

    public Duration(int durationid, String duration) {
        this.durationid = durationid;
        this.duration = duration;
    }


    public int getDurationid() {
        return durationid;
    }

    public void setDurationid(int durationid) {
        this.durationid = durationid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
