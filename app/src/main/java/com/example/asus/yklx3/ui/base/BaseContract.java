package com.example.asus.yklx3.ui.base;

/**
 * Created by asus on 2018/5/31.
 */

public interface BaseContract {
    interface BaseView {
        void showLoading();
        void dismissLoading();
    }
    interface BasePresenter<T extends BaseView>{
        void attchView(T view);
        void detachView();
    }
}
