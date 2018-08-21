package com.yqhd.wanandroid.launcher.request;

import android.content.Context;

import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;

/**
 * Author : xiongqiwei
 * Date : 2018/8/16
 * Project : PortalDemoLauncher
 */
public class RequstDao {
    public static void Login(Context context, UserLoginPostReq userLoginPostReq, MPaasAPIUtils.OnCompleteListener<String> listener){
        MPaasAPIUtils<String> utils = new MPaasAPIUtils<String>(context);
        utils.setTimeOut(3000)
                .targetClass(String.class)
                .functionType(ReqestType.LOGIN,userLoginPostReq)
                .monitor(listener);
    }
}
