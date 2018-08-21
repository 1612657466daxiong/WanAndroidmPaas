package com.yqhd.wanandroid.launcher.app;

import android.app.Application;

import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.monitor.track.TrackIntegrator;

/**
 * Created by seker on 16/2/18.
 */
public class MockLauncherApplicationAgent extends LauncherApplicationAgent {

    public MockLauncherApplicationAgent(Application context, Object bundleContext) {
        super(context, bundleContext);

    }

    @Override
    public void preInit() {
        super.preInit();

    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
