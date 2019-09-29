package com.whut.jarvis.askmeclient.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.whut.jarvis.askmeclient.bean.ServerInfo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jarvis on 2019/9/29.
 */

public class LoginUtil {
    private OkHttpClient client = null;
    private Retrofit retrofit = null;

    public LoginUtil() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerInfo.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public Observable<Integer> loginUser(final String username, final String password){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                SendRequest sendRequest = retrofit.create(SendRequest.class);

                Call<ResponseBody> call = sendRequest.loginUser(username,password);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Gson gson = new Gson();
                        HashMap<String,Integer> map = new HashMap<String,Integer>();
                        Type type = new TypeToken<HashMap<String, Integer>>() {}.getType();
                        try {
                            map = gson.fromJson(response.body().string(),type);
                            int statuscode = (int) map.get("statuscode");
                            emitter.onNext(statuscode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
