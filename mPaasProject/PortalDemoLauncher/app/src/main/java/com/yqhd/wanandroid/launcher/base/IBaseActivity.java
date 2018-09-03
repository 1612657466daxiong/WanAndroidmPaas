package com.yqhd.wanandroid.launcher.base;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.alipay.mobile.framework.app.ui.BaseActivity;

import io.reactivex.disposables.Disposable;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public abstract class IBaseActivity extends BaseActivity implements IBaseView{
    protected Disposable mDisposable;

    @Override
    public void disposable(Disposable d) {
        mDisposable = d;
    }

}
