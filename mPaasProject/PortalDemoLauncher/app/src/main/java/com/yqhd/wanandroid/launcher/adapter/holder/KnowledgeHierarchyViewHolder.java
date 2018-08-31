package com.yqhd.wanandroid.launcher.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yqhd.wanandroid.launcher.R;

/**
 * Author : xiongqiwei
 * Date : 2018/8/30
 * Project : PortalDemoLauncher
 */
public class KnowledgeHierarchyViewHolder extends BaseViewHolder{
    TextView mTitle;
    TextView mContent;
    public KnowledgeHierarchyViewHolder(View view) {
        super(view);
        mTitle= view.findViewById(R.id.item_knowledge_hierarchy_title);
        mContent = view.findViewById(R.id.item_knowledge_hierarchy_content);
    }
}
