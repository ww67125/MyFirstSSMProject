package com.etc.entity;

import java.util.Date;

public class News {

    private int newsid;
    private String title;
    private String content;
    private Date pubtime = new Date();

    private NewsType newstype;   //多对一
    private User user;  		 //多对一

    private String attachment = "";
    private String truename = "";
    private int downloadscore;
    private int downloadcount;

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    public NewsType getNewstype() {
        return newstype;
    }

    public void setNewstype(NewsType newstype) {
        this.newstype = newstype;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public int getDownloadscore() {
        return downloadscore;
    }

    public void setDownloadscore(int downloadscore) {
        this.downloadscore = downloadscore;
    }

    public int getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(int downloadcount) {
        this.downloadcount = downloadcount;
    }
}
