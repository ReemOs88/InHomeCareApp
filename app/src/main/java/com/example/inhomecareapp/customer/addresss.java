package com.example.inhomecareapp.customer;

import com.google.android.material.textfield.TextInputEditText;

public class addresss {
    String str,bul,floor,mobi,locat,id;

    public addresss() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public addresss(String str, String bul, String floor, String mobi, String locat, String id) {
        this.str = str;
        this.bul = bul;
        this.floor = floor;
        this.mobi = mobi;
        this.locat = locat;
        this.id=id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getBul() {
        return bul;
    }

    public void setBul(String bul) {
        this.bul = bul;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getMobi() {
        return mobi;
    }

    public void setMobi(String mobi) {
        this.mobi = mobi;
    }

    public String getLocat() {
        return locat;
    }

    public void setLocat(String locat) {
        this.locat = locat;
    }
}

