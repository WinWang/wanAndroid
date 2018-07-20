package com.winwang.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.NavigationAdapter;
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.model.GroupChildItem;
import com.winwang.wanandroid.model.NaviGroupItem;
import com.winwang.wanandroid.model.NavigationListData;
import com.winwang.wanandroid.present.NavigationPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationFragment extends BaseFragment<NavigationPresent> {
    @BindView(R.id.rv_frag_navi)
    RecyclerView rvFragNavi;
    @BindView(R.id.refresh_navi)
    SmartRefreshLayout refreshNavi;

    @Override
    public void getNetData() {
        getP().getNaviData();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        refreshNavi.setRefreshHeader(new DropBoxHeader(context));
        refreshNavi.setEnableLoadMore(false);
        refreshNavi.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNetData();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getNetData();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public NavigationPresent newP() {
        return new NavigationPresent();
    }

    public void setNaviData(List<NavigationListData> data) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (NavigationListData datum : data) {
            NaviGroupItem naviGroupItem = new NaviGroupItem(datum.getName());
            GroupChildItem groupChildItem = new GroupChildItem();
            groupChildItem.setArticles(datum.getArticles());
            naviGroupItem.addSubItem(groupChildItem);
            res.add(naviGroupItem);
        }
        refreshNavi.finishRefresh(1000);
        NavigationAdapter navigationAdapter = new NavigationAdapter(res, context);
        rvFragNavi.setLayoutManager(new LinearLayoutManager(context));
        rvFragNavi.setAdapter(navigationAdapter);
        navigationAdapter.expandAll();
    }

}
