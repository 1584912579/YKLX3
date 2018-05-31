package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/31.
 */

public class UpdateCartsApi {
    private static UpdateCartsApi updateCartsApi;
    private UpdateCartsApiService updateCartsApiService;

    private UpdateCartsApi(UpdateCartsApiService updateCartsApiService) {
        this.updateCartsApiService = updateCartsApiService;
    }

    public static UpdateCartsApi getupdateCartsApi(UpdateCartsApiService updateCartsApiService) {
        if (updateCartsApi == null) {
            updateCartsApi = new UpdateCartsApi(updateCartsApiService);
        }
        return updateCartsApi;
    }

    public Observable<BaseBean> getupdateCartsApi(String uid, String sellerid, String pid, String num, String selected) {
        return updateCartsApiService.updateCarts( uid, sellerid, pid,  num, selected );
    }

}
