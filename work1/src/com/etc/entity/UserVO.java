package com.etc.entity;

import java.io.Serializable;

public class UserVO implements Serializable {
    private String username = "";
    private String gender = "";
    private String job = "";

    private String regdatetime = "全部时间";
    private String begintime = "";
    private String endtime = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRegdatetime() {
        return regdatetime;
    }

    public void setRegdatetime(String regdatetime) {
        this.regdatetime = regdatetime;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
