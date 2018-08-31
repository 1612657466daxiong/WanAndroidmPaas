package com.yqhd.wanandroid.launcher.adapter.holder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yqhd.wanandroid.launcher.R;


/**
 * Author : xiongqiwei
 * Date : 2018/8/27
 * Project : PortalDemoLauncher
 */
public class FeedArticleViewHolder extends BaseViewHolder {
    CardView mItemGroup;
    ImageView mItemLikeIv;
    TextView mItemTile, mItemAuthor,mTaGreenTv,mTaRedTv,mItemChapterName,mItemNiceDate;

    public FeedArticleViewHolder(View view) {
        super(view);
        initView(view);
    }

    private void initView(View view) {
        mItemGroup = view.findViewById(R.id.item_search_pager_group);
        mItemLikeIv = view.findViewById(R.id.item_search_pager_like_iv);
        mItemTile = view.findViewById(R.id.item_search_pager_title);
        mItemAuthor = view.findViewById(R.id.item_search_pager_author);
        mTaGreenTv = view.findViewById(R.id.item_search_pager_tag_green_tv);
        mTaRedTv = view.findViewById(R.id.item_search_pager_tag_red_tv);
        mItemChapterName = view.findViewById(R.id.item_search_pager_chapterName);
        mItemNiceDate = view.findViewById(R.id.item_search_pager_niceDate);
    }
}
