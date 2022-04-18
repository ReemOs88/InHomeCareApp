package com.example.inhomecareapp.customer;

import java.io.Serializable;

public class contract_data implements Serializable {
    String horstay,state_cat,age,caregiver,address,servise_duration,frequentation,date;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServise_duration() {
        return servise_duration;
    }

    public void setServise_duration(String servise_duration) {
        this.servise_duration = servise_duration;
    }

    public String getFrequentation() {
        return frequentation;
    }

    public void setFrequentation(String frequentation) {
        this.frequentation = frequentation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public contract_data() {
    }

    public contract_data(String horstay, String state_cat, String age ) {
        this.horstay = horstay;
        this.state_cat = state_cat;
        this.age = age;
    }

    public String getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(String caregiver) {
        this.caregiver = caregiver;
    }

    public String getHorstay() {
        return horstay;
    }

    public void setHorstay(String horstay) {
        this.horstay = horstay;
    }

    public String getState_cat() {
        return state_cat;
    }

    public void setState_cat(String state_cat) {
        this.state_cat = state_cat;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

