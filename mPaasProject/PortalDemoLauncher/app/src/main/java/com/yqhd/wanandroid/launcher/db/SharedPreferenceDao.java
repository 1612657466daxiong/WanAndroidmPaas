package com.yqhd.wanandroid.launcher.db;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Author : xiongqiwei
 * Date : 2018/8/28
 * Project : PortalDemoLauncher
 */
public class SharedPreferenceDao  {
  private static final String SHARE_NAME = "saveUserInfo";
  private static  SharedPreferenceDao instance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor meditor;
    private static final String SHARE_KEY_USER_NAME="share_key_user_name";
    private static final String SHARE_KEY_PWD = "share_key_pwd";

    public SharedPreferenceDao(Context context){
        mSharedPreferences =context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
        meditor=mSharedPreferences.edit();
    }

    public static SharedPreferenceDao getInstance(Context context){
        if (instance==null){
            instance= new SharedPreferenceDao(context);
        }
        return instance;
    }

    public void saveUser(String name){
        meditor.putString(SHARE_KEY_USER_NAME,name);
        meditor.commit();
    }
    public void removeUser(){
        meditor.remove(SHARE_KEY_USER_NAME);
        meditor.commit();
    }
    public String getUser(){
        return mSharedPreferences.getString(SHARE_KEY_USER_NAME,null);
    }

    public void savePwd(String name){
        meditor.putString(SHARE_KEY_PWD,name);
        meditor.commit();
    }
    public void removePwd(){
        meditor.remove(SHARE_KEY_PWD);
        meditor.commit();
    }
    public String getPwd(){
        return mSharedPreferences.getString(SHARE_KEY_PWD,null);
    }
}
