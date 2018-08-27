package com.yqhd.wanandroid.launcher.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yqhd.wanandroid.launcher.bean.FeedArticleData;

import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/27
 * Project : PortalDemoLauncher
 */
public class ArticleListAdapter extends BaseQuickAdapter<FeedArticleData,FeedArticleViewHolder>{


    public ArticleListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FeedArticleViewHolder helper, FeedArticleData item) {

    }
}
