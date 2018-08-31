package com.yqhd.wanandroid.launcher.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.holder.KnowledgeHierarchyViewHolder;
import com.yqhd.wanandroid.launcher.bean.KnowledgeHierarchyData;
import com.yqhd.wanandroid.launcher.utils.CommonUtils;

import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/30
 * Project : PortalDemoLauncher
 */
public class KnowledgeHierarchyAdapter  extends BaseQuickAdapter<KnowledgeHierarchyData,KnowledgeHierarchyViewHolder>{


    public KnowledgeHierarchyAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(KnowledgeHierarchyViewHolder helper, KnowledgeHierarchyData item) {
        if(item.getName() == null) {
            return;
        }
        helper.setText(R.id.item_knowledge_hierarchy_title, item.getName());
        helper.setTextColor(R.id.item_knowledge_hierarchy_title, CommonUtils.randomColor());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder content = new StringBuilder();
        for (KnowledgeHierarchyData data: item.getChildren()) {
            content.append(data.getName()).append("   ");
        }
        helper.setText(R.id.item_knowledge_hierarchy_content, content.toString());
    }
}
