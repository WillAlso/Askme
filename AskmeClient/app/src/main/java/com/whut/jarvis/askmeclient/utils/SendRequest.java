package com.whut.jarvis.askmeclient.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jarvis on 2019/9/28.
 */

public interface SendRequest {

    @GET("sendmail")
    Call<ResponseBody> sendMail(@Query("email") String email);

    @GET("signupuser")
    Call<ResponseBody> signUpUser(@Query("username") String username,@Query("email")String email,@Query("password")String password, @Query("identity") int identity);

    @GET("loginuser")
    Call<ResponseBody> loginUser(@Query("username") String username,@Query("password")String password);
}
