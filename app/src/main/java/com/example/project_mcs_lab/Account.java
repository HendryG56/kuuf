package com.example.project_mcs_lab;

import android.os.Parcel;
import android.os.Parcelable;

public class Account {
    private Integer userid;
    private String username;
    private String password;
    private String phonenumber;
    private String dateofbirth;
    private String gender;
    private Integer nominal;

    public Account(Integer userid, String username, String password, String phonenumber,String dateofbirth, String gender, Integer nominal) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.nominal = nominal;
    }

    public Account() {

    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

