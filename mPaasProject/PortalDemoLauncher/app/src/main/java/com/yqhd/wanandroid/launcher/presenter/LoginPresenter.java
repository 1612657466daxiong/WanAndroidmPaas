package com.yqhd.wanandroid.launcher.presenter;

import android.content.Context;
import android.util.Log;

import com.yqhd.wanandroid.launcher.base.BaseObserver;
import com.yqhd.wanandroid.launcher.base.IBaseView;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.contract.LoginContract;
import com.yqhd.wanandroid.launcher.model.LoginModel;

import io.reactivex.disposables.Disposable;


/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class LoginPresenter extends BaseObserver<Result<User>> implements LoginContract.Presenter{
    private LoginModel model;
    private LoginContract.View view;

    public LoginPresenter(Context mContext, LoginContract.View view) {
        super(mContext, view);
        this.view = view;
        this.model = new LoginModel();
    }

    @Override
    public void Login(String userName, String pwd) {
        view.showLoadDialog();
        model.Login(userName,pwd,this);
    }


    @Override
    public void onSubscribe(Disposable d) {
        view.disposable(d);
    }

    @Override
    public void onError(Throwable e) {
        view.dissmissLoad();
        Log.e("LoginError",e.toString());
        view.showDataFail(1,"失败");
    }

    @Override
    public void onNext(Result<User> userResult) {
        view.showDataSuccess(userResult.getData());
    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.dissmissLoad();
    }
}
