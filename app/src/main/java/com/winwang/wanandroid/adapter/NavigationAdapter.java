package com.winwang.wanandroid.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.model.NaviGroupItem;
import com.winwang.wanandroid.model.NavigationListData;

import java.util.List;

public class NavigationAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NavigationAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.header_group_navi);//头布局
        addItemType(1, R.layout.child_group_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case 0:
                NaviGroupItem item1 = (NaviGroupItem) item;
                helper.setText(R.id.tv_group_header, item1.getName());
                break;
            case 1:
                NavigationListData item2 = (NavigationListData) (item);
                List<FeedArticleData> articles = item2.getArticles();

                break;
        }
    }
}
