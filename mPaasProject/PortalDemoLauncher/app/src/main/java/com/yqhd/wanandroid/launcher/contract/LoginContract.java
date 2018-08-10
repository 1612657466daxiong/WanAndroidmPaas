package com.yqhd.wanandroid.launcher.contract;


import com.yqhd.wanandroid.launcher.base.IBaseView;
import com.yqhd.wanandroid.launcher.bean.User;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class LoginContract {
    public interface View extends IBaseView{
        void showDataSuccess(User data);
        void showDataFail(int code,String msg);

    }

    public interface Presenter{
        void Login(String userName,String pwd);
    }
}
