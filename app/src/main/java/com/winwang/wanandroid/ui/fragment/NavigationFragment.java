package com.winwang.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.NavigationAdapter;
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.model.FeedArticleData;
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

    @Override
    public void getNetData() {
        getP().getNaviData();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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
        rvFragNavi.setLayoutManager(new LinearLayoutManager(context));
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (NavigationListData datum : data) {
            NaviGroupItem naviGroupItem = new NaviGroupItem(datum.getName());
            res.add(naviGroupItem);

        }
        NavigationAdapter navigationAdapter = new NavigationAdapter(res);
        rvFragNavi.setAdapter(navigationAdapter);


    }


}
