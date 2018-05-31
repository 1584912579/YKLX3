package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.GetAdBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/31.
 */

public class GetAdApi {
    private static GetAdApi getAdApi;
    private GetAdApiService getAdApiService;
    private GetAdApi (GetAdApiService getAdApiService){
        this.getAdApiService=getAdApiService;
    }
    public static GetAdApi getGetAdApi(GetAdApiService getAdApiService){
        if (getAdApi==null){
            getAdApi=new GetAdApi(getAdApiService);
        }
        return getAdApi;
    }
    public Observable<GetAdBean> getGetAdApi(){
        return getAdApiService.getGetAdApi();
    }
}
