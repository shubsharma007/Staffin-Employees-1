package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsMix {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title_name")
    @Expose
    private String titleName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("img1")
    @Expose
    private String img1;
    @SerializedName("img2")
    @Expose
    private String img2;
    @SerializedName("img3")
    @Expose
    private String img3;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("add_member")
    @Expose
    private String addMember;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddMember() {
        return addMember;
    }

    public void setAddMember(String addMember) {
        this.addMember = addMember;
    }



    private int month;


    public EventsMix(Integer id, String titleName, String image, String img1, String img2, String img3, String location, String description, String date, String addMember,  int month) {
        this.id = id;
        this.titleName = titleName;
        this.image = image;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.location = location;
        this.description = description;
        this.date = date;
        this.addMember = addMember;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
