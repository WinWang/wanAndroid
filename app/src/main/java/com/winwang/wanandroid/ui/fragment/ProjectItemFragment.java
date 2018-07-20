package com.winwang.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.ProjectListAdapter;
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.base.Constant;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.present.ProjectItemPresent;
import com.winwang.wanandroid.ui.activity.WebDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.router.Router;

public class ProjectItemFragment extends BaseFragment<ProjectItemPresent> {
    @BindView(R.id.rv_project_item)
    RecyclerView rvProjectItem;
    @BindView(R.id.refresh_project_item)
    SmartRefreshLayout refreshProjectItem;
    private int index = 1;
    private int classifyId;
    ArrayList<FeedArticleData> dataList = new ArrayList<>();
    private ProjectListAdapter listAdapter;

    @Override
    public void getNetData() {
        getP().getProjectData(index, classifyId);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        refreshProjectItem.setPrimaryColorsId(R.color.colorPrimary);
        refreshProjectItem.setRefreshHeader(new DeliveryHeader(context));
        refreshProjectItem.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                index++;
                getP().getProjectData(index, classifyId);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                index = 1;
                getP().getProjectData(index, classifyId);
            }
        });

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Bundle arguments = getArguments();
        classifyId = arguments.getInt("classifyId");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvProjectItem.setLayoutManager(linearLayoutManager);
        listAdapter = new ProjectListAdapter(R.layout.item_project_list, dataList);
        rvProjectItem.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context)
                        .to(WebDetailActivity.class)
                        .putString(Constant.ARTICAL_URL, dataList.get(position).getLink())
                        .putString(Constant.ARTICAL_Title, dataList.get(position).getTitle())
                        .launch();
            }
        });
        getNetData();

    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_item_layout;
    }

    @Override
    public ProjectItemPresent newP() {
        return new ProjectItemPresent();
    }

    public void setListData(List<FeedArticleData> data) {
        if (index == 1) {
            refreshProjectItem.finishRefresh(1000);
            dataList.clear();
        } else {
            refreshProjectItem.finishLoadMore(500);
        }
        dataList.addAll(data);
        listAdapter.notifyDataSetChanged();
    }


}
