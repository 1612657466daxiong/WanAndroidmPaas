package com.yqhd.wanandroid.launcher.widget.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author : xiongqiwei
 * Date : 2018/8/24
 * Project : PortalDemoLauncher
 */
public class ScrollingViewBehavior extends CoordinatorLayout.Behavior<View>{
    public ScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
      return dependency instanceof AppBarLayout;

    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getHeight();
        child.setTop(offset);
        return true;
    }
}
