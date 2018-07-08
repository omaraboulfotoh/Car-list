package com.omar.carlist.api;


import com.omar.carlist.app.data.api.CarListResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("carsonline")
    Observable<Response<CarListResponse>> getCarList(@Query("Ticks") Long ticks);
}
