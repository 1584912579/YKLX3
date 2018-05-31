package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class DeleteCartApi {
    private static DeleteCartApi deleteCartApi;
    private DeleteCartApiServlce deleteCartApiServlce;

    private DeleteCartApi(DeleteCartApiServlce deleteCartApiServlce) {
        this.deleteCartApiServlce = deleteCartApiServlce;
    }

    public static DeleteCartApi getDeleteCartApi(DeleteCartApiServlce deleteCartApiServlce) {
        if (deleteCartApi == null) {
            deleteCartApi = new DeleteCartApi(deleteCartApiServlce);
        }
        return deleteCartApi;
    }

    public Observable<BaseBean> getDeleteCartApi(String uid, String pid ) {
        return deleteCartApiServlce.getDeleteCart( uid, pid);
    }
}
