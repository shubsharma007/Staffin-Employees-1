package com.example.staffinemployees.Response;

import java.util.List;

public class MyMonth {
    private int id;
    private String title;
    private String img;
    private String location;
    private String desc;
    private String date;
    private List<MembersOfEvent> membersOfEventList;

    public MyMonth(int id, String title, String img, String location, String desc, String date, List<MembersOfEvent> membersOfEventList) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.location = location;
        this.desc = desc;
        this.date = date;
        this.membersOfEventList = membersOfEventList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MembersOfEvent> getMembersOfEventList() {
        return membersOfEventList;
    }

    public void setMembersOfEventList(List<MembersOfEvent> membersOfEventList) {
        this.membersOfEventList = membersOfEventList;
    }
}
