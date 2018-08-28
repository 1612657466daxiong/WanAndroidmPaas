package com.yqhd.wanandroid.launcher.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.alipay.mobile.framework.LauncherActivityAgent;
import com.alipay.mobile.monitor.track.TrackIntegrator;
import com.yqhd.wanandroid.launcher.ui.activity.LoginActivity;
import com.yqhd.wanandroid.launcher.ui.activity.MainActivity;

import java.util.concurrent.TimeUnit;

public class MockLauncherActivityAgent extends LauncherActivityAgent {
    @Override
    public void preInit(Activity activity) {
        super.preInit(activity);
        TrackIntegrator.getInstance().autoTrackClick(true);
        TrackIntegrator.getInstance().autoTrackPage(true);
    }

    @Override
    public void postInit(final Activity activity) {
        super.postInit(activity);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }, TimeUnit.SECONDS.toMillis(3));
    }

}
