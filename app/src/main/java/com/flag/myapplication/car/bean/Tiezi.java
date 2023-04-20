package com.flag.myapplication.car.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "Tiezi")
public class Tiezi implements Serializable {
    @Column(name = "id", isId = true,autoGen=true)

    private int id;
    @Column(name = "imgurl")

    private String imgurl;

    @Column(name = "userid")
    private int userid;

    @Column(name = "username")

    private String username;
    @Column(name = "zan")

    private int zan;
    @Column(name = "time")
    private String time;

    @Column(name = "titile")
    private String titile;


    @Column(name = "pingjianum")
    private int pingjianum;




    @Column(name = "neirong")
    private String neirong;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPingjianum() {
        return pingjianum;
    }

    public void setPingjianum(int pingjianum) {
        this.pingjianum = pingjianum;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }



    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }


}