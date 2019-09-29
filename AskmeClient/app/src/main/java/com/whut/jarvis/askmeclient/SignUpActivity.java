package com.whut.jarvis.askmeclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whut.jarvis.askmeclient.bean.ServerInfo;
import com.whut.jarvis.askmeclient.utils.SendRequest;
import com.whut.jarvis.askmeclient.utils.SignUpUtil;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    private EditText text_signup_username = null;
    private EditText text_signup_mail = null;
    private EditText text_signup_password = null;
    private EditText text_signup_repassword = null;
    private EditText text_signup_vercode = null;
    private Button btn_signup_sendcode = null;
    private Button btn_signup_exit = null;
    private Button btn_signup_register = null;
    private SignUpUtil signUpUtil = null;
    private String mycode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        text_signup_username = findViewById(R.id.text_signup_username);
        text_signup_mail = findViewById(R.id.text_signup_mail);
        text_signup_password = findViewById(R.id.text_signup_password);
        text_signup_repassword = findViewById(R.id.text_signup_repassword);
        text_signup_vercode = findViewById(R.id.text_signup_vercode);
        btn_signup_sendcode = findViewById(R.id.btn_signup_sendcode);
        btn_signup_exit = findViewById(R.id.btn_signup_exit);
        btn_signup_register = findViewById(R.id.btn_signup_register);

        signUpUtil = new SignUpUtil();
        btn_signup_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = text_signup_mail.getText().toString().trim();
                if (email == null || TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this,"请输入邮箱", LENGTH_SHORT).show();
                    return;
                }
                signUpUtil.getCode(email)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                setMycode(s);
                                Toast.makeText(SignUpActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        btn_signup_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_signup_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = text_signup_username.getText().toString().trim();
                String email = text_signup_mail.getText().toString().trim();
                String password = text_signup_password.getText().toString().trim();
                String repassword = text_signup_repassword.getText().toString().trim();
                String vercode = text_signup_vercode.getText().toString().trim();
                if (username == null || TextUtils.isEmpty(username) ||
                        email == null || TextUtils.isEmpty(email) ||
                        password == null || TextUtils.isEmpty(password) ||
                        repassword == null || TextUtils.isEmpty(repassword) ||
                        vercode == null || TextUtils.isEmpty(vercode)){
                    Toast.makeText(SignUpActivity.this,"请输入必填项",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!getMycode().equals(vercode)){
                    Toast.makeText(SignUpActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(repassword)){
                    Toast.makeText(SignUpActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                signUpUtil.signUpUser(username,email,password)
                    .observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            }
        });
    }

    public String getMycode() {
        return mycode;
    }

    public void setMycode(String mycode) {
        this.mycode = mycode;
    }
}
