package com.yqhd.wanandroid.launcher.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.bean.loginBean;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;
import com.yqhd.wanandroid.launcher.utils.StatusBarUtil;

public class LoginActivity extends BaseActivity {
    private EditText mEdPassword,mEdUsername;

    private Button mBtnLogin;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEdUsername = (EditText) findViewById(R.id.ed_username);
        mEdPassword = (EditText) findViewById(R.id.ed_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mToolbar = findViewById(R.id.toolbar);
        initToolbar();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequst();
            }
        });
    }

    private void LoginRequst() {

        UserLoginPostReq userLoginPostReq = new UserLoginPostReq();

        userLoginPostReq._requestBody = new loginBean("doraemon","123456789");
        RequstDao.Login(this, userLoginPostReq, new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(LoginActivity.this, "成功"+result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginActivity.this, "失败"+error, Toast.LENGTH_SHORT).show();
                Log.e("login fail",error);
            }
        });
    }

    private void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
//        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

}
