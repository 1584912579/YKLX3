package com.example.asus.yklx3.ui.shopping.contract;


import com.example.asus.yklx3.bean.GetCartsBean;
import com.example.asus.yklx3.bean.SellerBean;
import com.example.asus.yklx3.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/29.
 */

public interface ShopCartContract {
    interface View extends BaseContract.BaseView{
        void getCartssuess(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);
        void getupdateCartssuess();
        void getDeleteCartsSuccess();
    }
    interface Presnter extends BaseContract.BasePresenter<View>{
        void getCartssuess(String uid);
        void getupdateCartssuess(String uid, String sellerid, String pid, String num, String selected);
        void getDeleteCartsSuccess(String uid, String pid);
    }
}
