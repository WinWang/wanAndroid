package com.winwang.wanandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.FeedArticleData;

import java.util.List;

public class NaviChildAdapter extends BaseQuickAdapter<FeedArticleData, BaseViewHolder> {

    public NaviChildAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData item) {
        helper.setText(R.id.qmrd_child, item.getTitle());
    }
}
