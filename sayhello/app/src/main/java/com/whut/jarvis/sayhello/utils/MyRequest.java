package com.whut.jarvis.sayhello.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jarvis on 2019/9/28.
 */

public interface MyRequest {
    @GET("sayhello")
    Call<ResponseBody> getHello();
}
