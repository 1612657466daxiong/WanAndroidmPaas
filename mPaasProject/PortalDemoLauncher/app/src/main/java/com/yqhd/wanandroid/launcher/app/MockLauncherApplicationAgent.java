package com.yqhd.wanandroid.launcher.app;

import android.app.Application;

import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.common.RpcService;
import com.alipay.mobile.monitor.track.TrackIntegrator;
import com.yqhd.wanandroid.launcher.request.interceptor.CommonInterceptor;

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
        RpcService rpcService = getMicroApplicationContext().findServiceByInterface(RpcService.class.getName());
        rpcService.addRpcInterceptor(OperationType.class,new CommonInterceptor());
    }
}
