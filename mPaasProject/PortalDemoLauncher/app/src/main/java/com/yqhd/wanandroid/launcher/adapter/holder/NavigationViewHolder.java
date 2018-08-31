package com.yqhd.wanandroid.launcher.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yqhd.wanandroid.launcher.R;
import com.zhy.view.flowlayout.FlowLayout;

/**
 * Author : xiongqiwei
 * Date : 2018/8/31
 * Project : PortalDemoLauncher
 */
public class NavigationViewHolder extends BaseViewHolder {
    TextView mTitle;
    FlowLayout mFlayout;

    public NavigationViewHolder(View view) {
        super(view);
        initView(view);
    }

    private void initView(View view) {
        mFlayout = view.findViewById(R.id.item_navigation_flow_layout);
        mTitle = view.findViewById(R.id.item_navigation_tv);
    }
}
