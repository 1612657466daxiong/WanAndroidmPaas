package com.yqhd.wanandroid.launcher.model;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yqhd.wanandroid.launcher.api.ApiMethods;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.bean.loginBean;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;
import com.yqhd.wanandroid.launcher.ui.activity.LoginActivity;
import com.yqhd.wanandroid.launcher.ui.activity.MainActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;


/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class LoginModel {
    public void Login(String username, String pwd, Observer observer){
        ApiMethods.Login(username,pwd).subscribe(observer);
    }


}
