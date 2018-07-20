package com.winwang.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.model.GroupChildItem;
import com.winwang.wanandroid.model.NaviGroupItem;
import com.winwang.wanandroid.model.NavigationListData;
import com.winwang.wanandroid.utils.ToastUtil;

import java.util.List;

public class NavigationAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    private Context context;

    public NavigationAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        addItemType(0, R.layout.header_group_navi);//头布局
        addItemType(1, R.layout.child_group_layout);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case 0:
                final NaviGroupItem item1 = (NaviGroupItem) item;
                helper.setText(R.id.tv_group_header, item1.getName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (item1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case 1:
                GroupChildItem item2 = (GroupChildItem) (item);
                final List<FeedArticleData> articles = item2.getArticles();
                RecyclerView rv = (RecyclerView) helper.getView(R.id.rv_child_group);
                FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
                flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
                flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
                NaviChildAdapter naviChildAdapter = new NaviChildAdapter(R.layout.item_child_navi_group, articles);
                rv.setLayoutManager(flexboxLayoutManager);
                rv.setAdapter(naviChildAdapter);
                naviChildAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtil.showToast(articles.get(position).getTitle());
                    }
                });
                break;
        }
    }
}
