package com.yqhd.wanandroid.launcher.base;

import android.app.Activity;

import io.reactivex.disposables.Disposable;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public abstract class BaseActivity extends Activity implements IBaseView{
    protected Disposable mDisposable;

    @Override
    public void disposable(Disposable d) {
        mDisposable = d;
    }

}
