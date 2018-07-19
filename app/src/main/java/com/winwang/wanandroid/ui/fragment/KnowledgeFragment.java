package com.winwang.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.KnowledgeAdapter;
import com.winwang.wanandroid.base.BaseLazyFragment;
import com.winwang.wanandroid.model.KnowledgeHierarchyData;
import com.winwang.wanandroid.present.KnowledgePresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeFragment extends BaseLazyFragment<KnowledgePresent> {
    @BindView(R.id.rv_frag_knowledge)
    RecyclerView rvFragKnowledge;
    @BindView(R.id.refresh_knowledge)
    SmartRefreshLayout refreshKnowledge;
    List<KnowledgeHierarchyData> dataList = new ArrayList<>();
    private KnowledgeAdapter knowledgeAdapter;

    @Override
    public void getNetData() {
        getP().getListData();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getNetData();
    }

    @Override
    public void bindEvent() {
        rvFragKnowledge.setLayoutManager(new LinearLayoutManager(context));
        knowledgeAdapter = new KnowledgeAdapter(R.layout.item_knowledge_hierarchy, dataList);
        rvFragKnowledge.setAdapter(knowledgeAdapter);
        refreshKnowledge.setEnableLoadMore(false);
        refreshKnowledge.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getP().getListData();
            }
        });
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public KnowledgePresent newP() {
        return new KnowledgePresent();
    }


    public void setRvData(List<KnowledgeHierarchyData> data) {
        refreshKnowledge.finishRefresh(1000);
        dataList.clear();
        dataList.addAll(data);
        knowledgeAdapter.notifyDataSetChanged();
    }


}
