package com.example.project_mcs_lab;

import android.os.Parcel;
import android.os.Parcelable;

public class Game {
    private Integer product_id;
    private String gamename;
    private Integer minplayer;
    private Integer maxplayer;
    private Integer gameprice;
    private Double longitude;
    private Double latitude;

    public Game(Integer product_id, String gamename, Integer minplayer, Integer maxplayer, Integer gameprice, Double longitude, Double latitude) {
        this.product_id = product_id;
        this.gamename = gamename;
        this.minplayer = minplayer;
        this.maxplayer = maxplayer;
        this.gameprice = gameprice;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Game() {

    }
    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public Integer getMinplayer() {
        return minplayer;
    }

    public void setMinplayer(Integer minplayer) {
        this.minplayer = minplayer;
    }

    public Integer getMaxplayer() {
        return maxplayer;
    }

    public void setMaxplayer(Integer maxplayer) {
        this.maxplayer = maxplayer;
    }

    public Integer getGameprice() {
        return gameprice;
    }

    public void setGameprice(Integer gameprice) {
        this.gameprice = gameprice;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
