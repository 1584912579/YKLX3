package com.example.asus.yklx3.ui.homepage.contract;

import com.example.asus.yklx3.bean.GetAdBean;
import com.example.asus.yklx3.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/31.
 */

public interface GetAdContract {
    interface View extends BaseContract.BaseView{
        void getGetAdSueess(GetAdBean getAdBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void GetAdPresenter();
    }
}
