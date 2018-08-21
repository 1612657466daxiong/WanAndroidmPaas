package com.yqhd.wanandroid.launcher.base;

import android.content.Context;

import io.reactivex.Observer;

import io.reactivex.disposables.Disposable;


/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class BaseObserver<T> implements Observer<T> {
    public Context mContext;
    public IBaseView view;

    public BaseObserver(Context mContext, IBaseView view) {
        this.mContext = mContext;
        this.view = view;
    }



    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        view.disposable(d);
    }

    @Override
    public void onNext(T t) {

    }
}
