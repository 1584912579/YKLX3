package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.GetAdBean;
import com.example.asus.yklx3.bean.GetCartsBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/31.
 */

public class GetCartsApi {
    private static GetCartsApi getCartsApi;
    private GetCartsApiService getCartsApiService;
    private GetCartsApi (GetCartsApiService getCartsApiService){
        this.getCartsApiService=getCartsApiService;
    }
    public static GetCartsApi getGetCartsApi(GetCartsApiService getCartsApiService){
        if (getCartsApi==null){
            getCartsApi=new GetCartsApi(getCartsApiService);
        }
        return getCartsApi;
    }
    public Observable<GetCartsBean> getGetCartsApi(String uid){
        return getCartsApiService.getGetCartsApi(uid);
    }
}
