package com.flag.myapplication.car.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "Renyuan")
public class Renyuan implements Serializable {
    @Column(name = "id", isId = true,autoGen=true)
    private int id;
    @Column(name = "beizhu")
    private String beizhu;

    @Column(name = "sexbiz")
    private String sexbiz;

    @Column(name = "xueli")

    private String xueli;
    @Column(name = "chengxiang")
    private String chengxiang;

    @Column(name = "time")
    private String time;

    @Column(name = "zongliang")
    private String zongliang;


    @Column(name = "userid")
    private int userid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getSexbiz() {
        return sexbiz;
    }

    public void setSexbiz(String sexbiz) {
        this.sexbiz = sexbiz;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getChengxiang() {
        return chengxiang;
    }

    public void setChengxiang(String chengxiang) {
        this.chengxiang = chengxiang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZongliang() {
        return zongliang;
    }

    public void setZongliang(String zongliang) {
        this.zongliang = zongliang;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}