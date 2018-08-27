package com.yqhd.wanandroid.launcher.api;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.bean.loginBean;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;
import com.yqhd.wanandroid.launcher.ui.activity.LoginActivity;
import com.yqhd.wanandroid.launcher.ui.activity.MainActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ibm on 2017/9/12.
 */

public class ApiMethods {
    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    /**
     * 用于
     * @param username 用户名
     * @param pwd       密码
     */
    public static Observable<Result<User>> Login(String username, String pwd){
       return RetrofitUtil.getInstance().getApiService().Login(username,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }





}
