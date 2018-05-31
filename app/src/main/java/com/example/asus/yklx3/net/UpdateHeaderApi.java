package com.example.asus.yklx3.net;

import com.example.asus.yklx3.bean.BaseBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by asus on 2018/5/22.
 */

public class UpdateHeaderApi {
    private static UpdateHeaderApi updateHeaderApi;
    private UpdateHeaderApiService updateHeaderApiService;

    private UpdateHeaderApi(UpdateHeaderApiService updateHeaderApiService) {
        this.updateHeaderApiService = updateHeaderApiService;
    }

    public static UpdateHeaderApi getUpdateHeaderApi(UpdateHeaderApiService updateHeaderApiService) {
        if (updateHeaderApi == null) {
            updateHeaderApi = new UpdateHeaderApi(updateHeaderApiService);
        }
        return updateHeaderApi;
    }

    public Observable<BaseBean> updateHeader(RequestBody uid, MultipartBody.Part file) {
        return updateHeaderApiService.updateHeader(uid, file);
    }
}
