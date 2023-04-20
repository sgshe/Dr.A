package com.flag.myapplication.car.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "Weather")
public class Weather implements Serializable {
    @Column(name = "id", isId = true,autoGen=true)
    private int id;
    @Column(name = "beizhu")
    private String beizhu;

    @Column(name = "wendu")
    private String wendu;

    @Column(name = "jiangshuiliang")
    private String jiangshuiliang;
    @Column(name = "shidu")

    private String shidu;

    @Column(name = "time")
    private String time;

    @Column(name = "fengli")
    private String fengli;


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

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getJiangshuiliang() {
        return jiangshuiliang;
    }

    public void setJiangshuiliang(String jiangshuiliang) {
        this.jiangshuiliang = jiangshuiliang;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}