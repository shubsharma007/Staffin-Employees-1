package com.example.staffinemployees.Response;

public class NationalCreatedMix implements java.io.Serializable{
    private String name;
    private String date;
    private String desc;
    private String type;


    public NationalCreatedMix(String name, String date, String desc, String type) {
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
