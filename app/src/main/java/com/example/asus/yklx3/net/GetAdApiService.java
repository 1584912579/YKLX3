package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.GetAdBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/31.
 */

public interface GetAdApiService {
    //@FormUrlEncoded
    @GET("ad/getAd")
    Observable<GetAdBean> getGetAdApi();
}
