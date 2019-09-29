package com.whut;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyRequest {

    @GET("sayhello")
    Call<ResponseBody> getCall();

}
