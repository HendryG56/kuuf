package com.example.project_mcs_lab;

public class History {
    private Integer id_tr;
    private String product_name;
    private String tr_date;
    private Integer product_price;

    public History(Integer id_tr, String product_name, String tr_date, Integer product_price) {
        this.id_tr = id_tr;
        this.product_name = product_name;
        this.tr_date = tr_date;
        this.product_price = product_price;
    }

    public History(){

    }

    public Integer getId_tr() {
        return id_tr;
    }

    public void setId_tr(Integer id_tr) {
        this.id_tr = id_tr;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getTr_date() {
        return tr_date;
    }

    public void setTr_date(String tr_date) {
        this.tr_date = tr_date;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }
}
