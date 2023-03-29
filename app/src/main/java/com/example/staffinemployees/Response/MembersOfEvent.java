package com.example.staffinemployees.Response;

public class MembersOfEvent {
    private int id;
    private String img;

    public MembersOfEvent(int id, String img) {
        this.id = id;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
