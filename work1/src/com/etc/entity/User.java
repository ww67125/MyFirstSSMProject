package com.etc.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class User {
    private int userid;
    @Length(min=5, max=10, message="303:用户名长度必须在5-10个字符之间")
    private String username="";
    @NotBlank(message="303:密码不能为空")
    private String password;
    private int score;
    private String photo;
    private String gender="";
    private String job="";
    private String interest;
    private Date regdatetime=new Date();

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

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Date getRegdatetime() {
        return regdatetime;
    }

    public void setRegdatetime(Date regdatetime) {
        this.regdatetime = regdatetime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username
                + ", password=" + password + ", score=" + score + ", photo="
                + photo + "]";
    }

}
