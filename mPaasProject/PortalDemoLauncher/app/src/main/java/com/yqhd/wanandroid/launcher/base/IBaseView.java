package com.yqhd.wanandroid.launcher.base;

import io.reactivex.disposables.Disposable;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public interface IBaseView {
    void disposable(Disposable d);
    void showLoadDialog();
    void dissmissLoad();
}
