package com.yqhd.wanandroid.launcher.app;

import android.app.Application;

/**
 * Author : xiongqiwei
 * Date : 2018/8/2
 * Project : PortalDemoLauncher
 */
public class WanAndroidApp extends Application {
    private static WanAndroidApp instance;

    public static synchronized void setInstance(Application context){
        instance = (WanAndroidApp) context;
    }
}
