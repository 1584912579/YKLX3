package com.example.asus.yklx3.ui.userimfo.contract;

import com.example.asus.yklx3.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/31.
 */

public interface UpdateHeaderContract {
    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath);
    }
}
