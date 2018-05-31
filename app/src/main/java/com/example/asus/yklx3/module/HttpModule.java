package com.example.asus.yklx3.module;

import com.example.asus.yklx3.net.DeleteCartApi;
import com.example.asus.yklx3.net.DeleteCartApiServlce;
import com.example.asus.yklx3.net.GetAdApi;
import com.example.asus.yklx3.net.GetAdApiService;
import com.example.asus.yklx3.net.GetCartsApi;
import com.example.asus.yklx3.net.GetCartsApiService;
import com.example.asus.yklx3.net.UpdateCartsApi;
import com.example.asus.yklx3.net.UpdateCartsApiService;
import com.example.asus.yklx3.net.UpdateHeaderApi;
import com.example.asus.yklx3.net.UpdateHeaderApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus on 2018/5/31.
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS);
    }
    @Provides
    GetAdApi provideGetAdApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        GetAdApiService getAdApiService = retrofit.create(GetAdApiService.class);
        return GetAdApi.getGetAdApi(getAdApiService);
    }
    @Provides
    GetCartsApi provideGetCartsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        GetCartsApiService cartsApiService = retrofit.create(GetCartsApiService.class);
        return GetCartsApi.getGetCartsApi(cartsApiService);
    }
    @Provides
    UpdateCartsApi provideUpdateCartsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        UpdateCartsApiService updateCartsApiService = retrofit.create(UpdateCartsApiService.class);
        return UpdateCartsApi.getupdateCartsApi(updateCartsApiService);
    }
    @Provides
    DeleteCartApi provideDeleteCartApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        DeleteCartApiServlce deleteCartApiServlce = retrofit.create(DeleteCartApiServlce.class);
        return DeleteCartApi.getDeleteCartApi(deleteCartApiServlce);
    }
    @Provides
    UpdateHeaderApi provideUpdateHeaderApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        UpdateHeaderApiService updateHeaderApiService = retrofit.create(UpdateHeaderApiService.class);
        return UpdateHeaderApi.getUpdateHeaderApi(updateHeaderApiService);
    }
}
