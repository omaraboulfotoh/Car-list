package com.omar.carlist.app.data.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omar.carlist.app.data.models.Car;

import java.util.ArrayList;

public class CarListResponse extends BaseResponse {

    @SerializedName("RefreshInterval")
    @Expose
    private int refreshInterval;
    @SerializedName("Ticks")
    @Expose
    private Long ticks;
    @SerializedName("Cars")
    @Expose
    private ArrayList<Car> cars;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public Long getTicks() {
        return ticks;
    }
}
