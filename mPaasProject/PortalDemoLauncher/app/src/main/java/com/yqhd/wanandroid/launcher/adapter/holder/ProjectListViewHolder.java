package com.yqhd.wanandroid.launcher.adapter.holder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yqhd.wanandroid.launcher.R;

/**
 * Author : xiongqiwei
 * Date : 2018/9/3
 * Project : PortalDemoLauncher
 */
public class ProjectListViewHolder extends BaseViewHolder {
    ImageView mProjectIv;
    TextView mTitle,mContentTv,mTimeTv,mAuthorTv,mInstallTv;
    public ProjectListViewHolder(View view) {
        super(view);
        initView(view);
    }

    private void initView(View view) {
        mProjectIv = view.findViewById(R.id.item_project_list_iv);
        mTitle = view.findViewById(R.id.item_project_list_title_tv);
        mContentTv = view.findViewById(R.id.item_project_list_content_tv);
        mTimeTv = view.findViewById(R.id.item_project_list_time_tv);
        mAuthorTv = view.findViewById(R.id.item_project_list_author_tv);
        mInstallTv = view.findViewById(R.id.item_project_list_install_tv);
    }
}
