package com.example.asus.yklx3.ui.shopping.presenter;


import com.example.asus.yklx3.bean.BaseBean;
import com.example.asus.yklx3.bean.GetCartsBean;
import com.example.asus.yklx3.bean.SellerBean;
import com.example.asus.yklx3.net.DeleteCartApi;
import com.example.asus.yklx3.net.GetCartsApi;
import com.example.asus.yklx3.net.UpdateCartsApi;
import com.example.asus.yklx3.ui.base.BasePresenter;
import com.example.asus.yklx3.ui.shopping.contract.ShopCartContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/29.
 */

public class ShopCartPresenter extends BasePresenter<ShopCartContract.View> implements ShopCartContract.Presnter {
    private GetCartsApi cartsApi;
    private UpdateCartsApi updateCartsApi;
    private DeleteCartApi deleteCartApi;
    @Inject
    public ShopCartPresenter(GetCartsApi cartsApi, UpdateCartsApi updateCartsApi, DeleteCartApi deleteCartApi) {
        this.cartsApi = cartsApi;
        this.updateCartsApi = updateCartsApi;
        this.deleteCartApi = deleteCartApi;
    }

    @Override
    public void getCartssuess(String uid) {
        cartsApi.getGetCartsApi(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<GetCartsBean, List<GetCartsBean.DataBean>>() {
                    @Override
                    public  List<GetCartsBean.DataBean> apply(GetCartsBean cartsBean) throws Exception {
                        return cartsBean.getData();
                    }
                }).subscribe(new Consumer<List<GetCartsBean.DataBean>>() {
            @Override
            public void accept(List<GetCartsBean.DataBean> dataBeans) throws Exception {
                List<SellerBean> groupList =new ArrayList<>();
                List<List<GetCartsBean.DataBean.ListBean>> childList =new ArrayList<>();
                for (int i = 0; i < dataBeans.size() ; i++) {
                    GetCartsBean.DataBean dataBean = dataBeans.get(i);
                    SellerBean sellerBean = new SellerBean();
                    sellerBean.setSellerid(dataBean.getSellerid());
                    sellerBean.setSellerName(dataBean.getSellerName());
                    sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                    groupList.add(sellerBean);

                    List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
                    childList.add(list);
                }
                if (mView!=null){
                    mView.getCartssuess(groupList,childList);
                }
            }
        });
    }

    private boolean isSellerProductAllSelect(GetCartsBean.DataBean dataBean) {
        List<GetCartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            GetCartsBean.DataBean.ListBean listBean = list.get(i);
            if (0==listBean.getSelected()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void getupdateCartssuess(String uid, String sellerid, String pid, String num, String selected) {
        updateCartsApi.getupdateCartsApi(uid,sellerid,pid,num,selected)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.getupdateCartssuess();
                }
            }
        });

    }

    @Override
    public void getDeleteCartsSuccess(String uid, String pid) {
        deleteCartApi.getDeleteCartApi(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.getDeleteCartsSuccess();
                }
            }
        });
    }
}
