package com.example.project_mcs_lab;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction{
    private Integer tr_id;
    private Integer user_id;
    private Integer product_id;
    private String tr_date;

    public Transaction(Integer tr_id, Integer user_id, Integer product_id, String tr_date) {
        this.tr_id = tr_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.tr_date = tr_date;
    }

    public Transaction(){

    }

    public Integer getTr_id() {
        return tr_id;
    }

    public void setTr_id(Integer tr_id) {
        this.tr_id = tr_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getTr_date() {
        return tr_date;
    }

    public void setTr_date(String tr_date) {
        this.tr_date = tr_date;
    }
}

