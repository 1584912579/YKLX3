package com.example.asus.yklx3.ui.homepage.presenter;

import android.test.suitebuilder.annotation.Suppress;

import com.example.asus.yklx3.bean.GetAdBean;
import com.example.asus.yklx3.net.GetAdApi;
import com.example.asus.yklx3.ui.base.BaseContract;
import com.example.asus.yklx3.ui.base.BasePresenter;
import com.example.asus.yklx3.ui.homepage.contract.GetAdContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/31.
 */

public class GetAdPresenter extends BasePresenter<GetAdContract.View> implements GetAdContract.Presenter {
    private GetAdApi getAdApi;
    @Inject
    public GetAdPresenter(GetAdApi getAdApi) {
        this.getAdApi = getAdApi;
    }

    @Override
    public void GetAdPresenter() {
        getAdApi.getGetAdApi()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetAdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetAdBean getAdBean) {
                        if (mView!=null){
                            mView.getGetAdSueess(getAdBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
