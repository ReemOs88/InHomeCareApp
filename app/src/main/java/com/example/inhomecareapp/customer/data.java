package com.example.inhomecareapp.customer;

public class data {
    String Street_number,
            Building_number,
            Floor_number,
            Mobile_number;
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = getStreet_number()+getBuilding_number()+Floor_number+Mobile_number;
    }

    public data(String street_number, String building_number, String floor_number, String mobile_number) {
        Street_number = street_number;
        Building_number = building_number;
        Floor_number = floor_number;
        Mobile_number = mobile_number;
    }
    public data() {
    }

    public String getStreet_number() {
        return Street_number;
    }

    public void setStreet_number(String street_number) {
        Street_number = street_number;
    }

    public String getBuilding_number() {
        return Building_number;
    }

    public void setBuilding_number(String building_number) {
        Building_number = building_number;
    }

    public String getFloor_number() {
        return Floor_number;
    }

    public void setFloor_number(String floor_number) {
        Floor_number = floor_number;
    }

    public String getMobile_number() {
        return Mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        Mobile_number = mobile_number;
    }
}


