package com.admin.domain;

import com.admin.utils.DateUtils;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-22
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class Announce {
    private Long announceId;
    private Date announceStartDate;
    private String announceStartDateStr;
    private String announceTitle;
    private String announceContent;

    public Long getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(Long announceId) {
        this.announceId = announceId;
    }

    public Date getAnnounceStartDate() {
        return announceStartDate;
    }

    public void setAnnounceStartDate(Date announceStartDate) {
        this.announceStartDate = announceStartDate;
    }

    public String getAnnounceTitle() {
        return announceTitle;
    }

    public void setAnnounceTitle(String announceTitle) {
        this.announceTitle = announceTitle;
    }

    public String getAnnounceContent() {
        return announceContent;
    }

    public void setAnnounceContent(String announceContent) {
        this.announceContent = announceContent;
    }
    
    public String getAnnounceStartDateStr() {
        if (announceStartDate != null) {
            announceStartDateStr = DateUtils.date2String(announceStartDate, "yyyy-MM-dd");
        }
        return announceStartDateStr;
    }

    public void setAnnounceStartDateStr(String announceStartDateStr) {
        this.announceStartDateStr = announceStartDateStr;
    }

    @Override
    public String toString() {
        return "Announce{" +
                "announceId=" + announceId +
                ", announceStartDate=" + announceStartDate +
                ", announceTitle='" + announceTitle + '\'' +
                ", announceContent='" + announceContent + '\'' +
                '}';
    }
}
