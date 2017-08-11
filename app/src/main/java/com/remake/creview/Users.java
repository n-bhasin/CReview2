package com.remake.creview;

import java.io.Serializable;

/**
 * Created by pc on 06-08-2017.
 */

public class Users implements Serializable{
    int id;
    String name;
    String email;
    String phone;
    String address;
   /* String dob;*/
    String quality;
    String service;
    String cleaniness;
    float ratings;

    public Users(){

    }
    public Users(int id, String name, String email, String phone, String address, /*String dob,*/ String quality, String service, String cleaniness, float ratings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        /*this.dob = dob;*/
        this.quality = quality;
        this.service = service;
        this.cleaniness = cleaniness;
        this.ratings = ratings;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
/*
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }*/

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCleaniness() {
        return cleaniness;
    }

    public void setCleaniness(String cleaniness) {
        this.cleaniness = cleaniness;
    }
    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "Customer Detail:" +
                "\n\nId: " + id +
                "\n\nName: " + name +
                "\n\nEmail: " + email +
                "\n\nPhone: " + phone +
                "\n\nAddress: " + address +
                "\n\nQuality: " + quality +
                "\n\nService: " + service +
                "\n\nCleaniness: " + cleaniness +
                "\n\nRatings: " + ratings
                ;
    }
}
