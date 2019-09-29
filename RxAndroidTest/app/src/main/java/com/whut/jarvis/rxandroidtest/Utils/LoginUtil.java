package com.whut.jarvis.rxandroidtest.Utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LoginUtil {

    private OkHttpClient client = null;

    public LoginUtil(){
        client = new OkHttpClient();
    }

    public Observable<String> signIn(final String url){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("mymessage",url);
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            String data = response.body().string();
                            if(data != null){
                                emitter.onNext(data);
                            }
                        }
                        emitter.onComplete();
                    }
                });

            }
        });
    }
}
