package com.yqhd.wanandroid.launcher.request;

import android.content.Context;

import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.request.req.ArticleListPageJsonGetReq;
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

    public static void GetBanner(Context context,MPaasAPIUtils.OnCompleteListener<String> listener){
        MPaasAPIUtils<String> utils = new MPaasAPIUtils<>(context);
        utils.setTimeOut(5000)
                .targetClass(String.class)
                .functionType(ReqestType.BANNER,null)
                .monitor(listener);
    }

    public static void GetFeedList(Context context, ArticleListPageJsonGetReq req,MPaasAPIUtils.OnCompleteListener<String> listener){
        MPaasAPIUtils<String> utils = new MPaasAPIUtils<>(context);
        utils.setTimeOut(5000)
                .targetClass(String.class)
                .functionType(ReqestType.GET_HOME_FEED_LIST,req)
                .monitor(listener);
    }

    public static void GetKnowledge(Context context,MPaasAPIUtils.OnCompleteListener<String> listener){
        MPaasAPIUtils<String> utils = new MPaasAPIUtils<>(context);
        utils.setTimeOut(5000)
                .targetClass(String.class)
                .functionType(ReqestType.GET_TREE_JSON,null)
                .monitor(listener);
    }


    public static void GetNavigation(Context context,MPaasAPIUtils.OnCompleteListener<String > listener){
        MPaasAPIUtils<String > utils = new MPaasAPIUtils<>(context);
        utils.setTimeOut(6000)
                .targetClass(String.class)
                .functionType(ReqestType.GET_NAVIGATION,null)
                .monitor(listener);
    }
}
