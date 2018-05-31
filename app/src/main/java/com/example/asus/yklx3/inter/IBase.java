package com.example.asus.yklx3.inter;

import android.view.View;

/**
 * Created by asus on 2018/5/31.
 */

public interface IBase {
    int getContentLayout();

    void inject();

    void initView(View view);
}
