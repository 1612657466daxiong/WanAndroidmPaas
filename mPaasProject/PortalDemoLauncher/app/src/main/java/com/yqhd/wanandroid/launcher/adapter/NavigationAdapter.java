package com.yqhd.wanandroid.launcher.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.holder.NavigationViewHolder;
import com.yqhd.wanandroid.launcher.app.Constants;
import com.yqhd.wanandroid.launcher.bean.FeedArticleData;
import com.yqhd.wanandroid.launcher.bean.NavigationListData;
import com.yqhd.wanandroid.launcher.ui.activity.WebActivity;
import com.yqhd.wanandroid.launcher.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/31
 * Project : PortalDemoLauncher
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationListData,NavigationViewHolder> {
    Context context;
    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListData> data,Context context) {
        super(layoutResId, data);
        this.context =context;
    }

    @Override
    protected void convert(NavigationViewHolder helper, final NavigationListData item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        final TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        final List<FeedArticleData> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(CommonUtils.dp2px(context,10), CommonUtils.dp2px(context,10),
                        CommonUtils.dp2px(context,10), CommonUtils.dp2px(context,10));
                tv.setText(name);
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra(Constants.ARTICLE_ID, mArticles.get(position).getId());
                        intent.putExtra(Constants.ARTICLE_TITLE,  mArticles.get(position).getTitle());
                        intent.putExtra(Constants.ARTICLE_LINK,  mArticles.get(position).getLink());
                        intent.putExtra(Constants.IS_COLLECT, mArticles.get(position).isCollect());
                        intent.putExtra(Constants.IS_COLLECT_PAGE, false);
                        intent.putExtra(Constants.IS_COMMON_SITE, false);
                        context.startActivity(intent);
                        return true;
                    }
                });
                return tv;
            }
        });
    }
}
