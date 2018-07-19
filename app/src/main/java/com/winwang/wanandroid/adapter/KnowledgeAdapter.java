package com.winwang.wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.KnowledgeHierarchyData;

import java.util.List;

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, BaseViewHolder> {

    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeHierarchyData item) {
        helper.setText(R.id.item_knowledge_hierarchy_title, item.getName());
        List<KnowledgeHierarchyData> children = item.getChildren();
        StringBuffer stringBuffer = new StringBuffer();
        for (KnowledgeHierarchyData child : children) {
            stringBuffer.append(child.getName() + "   ");
        }
        helper.setText(R.id.item_knowledge_hierarchy_content, stringBuffer.toString());
    }
}
