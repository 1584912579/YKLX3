package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.GetCartsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/31.
 */

public interface GetCartsApiService {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<GetCartsBean> getGetCartsApi(@Field("uid") String uid);
}
