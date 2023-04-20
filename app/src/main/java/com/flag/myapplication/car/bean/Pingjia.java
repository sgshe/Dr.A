package com.flag.myapplication.car.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "Pingjia")
public class Pingjia implements Serializable {

    @Column(name = "id", isId = true,autoGen=true)
    private int id;
    @Column(name = "xinwenid")
    private int xinwenid;

    @Column(name = "username")
    private String username;


    @Column(name = "liuyanneirong")
    private String liuyanneirong;

    @Column(name = "time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getXinwenid() {
        return xinwenid;
    }

    public void setXinwenid(int xinwenid) {
        this.xinwenid = xinwenid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLiuyanneirong() {
        return liuyanneirong;
    }

    public void setLiuyanneirong(String liuyanneirong) {
        this.liuyanneirong = liuyanneirong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
