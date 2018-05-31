package com.example.asus.yklx3.component;

import com.example.asus.yklx3.ui.homepage.MainActivity;
import com.example.asus.yklx3.module.HttpModule;
import com.example.asus.yklx3.ui.shopping.ShopActivity;
import com.example.asus.yklx3.ui.userimfo.UserInfoActivity;

import dagger.Component;

/**
 * Created by asus on 2018/5/31.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(MainActivity mainActivity);
    void inject(ShopActivity shopActivity);
    void inject(UserInfoActivity userInfoActivity);
}
