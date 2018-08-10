package com.yqhd.wanandroid.launcher.model;

import com.yqhd.wanandroid.launcher.api.ApiMethods;

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
