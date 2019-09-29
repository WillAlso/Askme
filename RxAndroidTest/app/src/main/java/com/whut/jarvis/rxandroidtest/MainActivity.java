package com.whut.jarvis.rxandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.whut.jarvis.rxandroidtest.Utils.LoginUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    private static final String TAG = "RxAndroidSamples";
    private Button btn_test = null;
    private TextView textView = null;
    private LoginUtil loginUtil = null;
    private String url = "http://10.120.74.119:8080/sayhello";

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_test = findViewById(R.id.btn_test);
        textView = findViewById(R.id.text_msg);
        loginUtil = new LoginUtil();
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUtil.signIn(url)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("mymessage","onSubscribe");
                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("mymessage",s);
                                textView.setText(s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("mymessage","onError");
                            }

                            @Override
                            public void onComplete() {
                                Log.d("mymessage","onComplete");
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    public void onRunSchedulerExampleButtonClicked(){
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.newThread())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            public Observable<String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(500);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }
}
