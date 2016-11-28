package com.exceptions.cognation.model;

import java.util.Date;

public class Feed {
    private Date date;
    private String photoUrl;
    private String shortDetails;
    private long status;
    private String title;

    public Feed(){}

    public Feed(Date date, String photoUrl, String shortDetails, long status, String title) {
        this.date = date;
        this.photoUrl = photoUrl;
        this.shortDetails = shortDetails;
        this.status = status;
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getShortDetails() {
        return shortDetails;
    }

    public long getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setShortDetails(String shortDetails) {
        this.shortDetails = shortDetails;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}