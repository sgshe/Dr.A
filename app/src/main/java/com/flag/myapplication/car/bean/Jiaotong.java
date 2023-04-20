package com.flag.myapplication.car.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "Jiaotong")
public class Jiaotong implements Serializable {
    @Column(name = "id", isId = true,autoGen=true)
    private int id;
    @Column(name = "beizhu")
    private String beizhu;

    @Column(name = "chujing")
    private String chujing;

    @Column(name = "carshi")

    private String carshi;
    @Column(name = "newcar")

    private String newcar;

    @Column(name = "time")
    private String time;

    @Column(name = "oldcar")
    private String oldcar;


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

    public String getChujing() {
        return chujing;
    }

    public void setChujing(String chujing) {
        this.chujing = chujing;
    }

    public String getCarshi() {
        return carshi;
    }

    public void setCarshi(String carshi) {
        this.carshi = carshi;
    }

    public String getNewcar() {
        return newcar;
    }

    public void setNewcar(String newcar) {
        this.newcar = newcar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOldcar() {
        return oldcar;
    }

    public void setOldcar(String oldcar) {
        this.oldcar = oldcar;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}