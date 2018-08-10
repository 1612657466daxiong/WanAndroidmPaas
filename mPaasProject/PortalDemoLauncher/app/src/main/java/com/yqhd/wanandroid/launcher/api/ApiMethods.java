package com.yqhd.wanandroid.launcher.api;


import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;

import io.reactivex.Observable;
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
