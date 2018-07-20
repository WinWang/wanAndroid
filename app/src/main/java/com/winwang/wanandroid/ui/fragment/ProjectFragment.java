package com.winwang.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.ProjectItemAdapter;
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.model.ProjectClassifyData;
import com.winwang.wanandroid.present.ProjectPresent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

public class ProjectFragment extends BaseFragment<ProjectPresent> {
    @BindView(R.id.tab_project)
    TabLayout tabProject;
    @BindView(R.id.vp_project)
    ViewPager vpProject;

    @Override
    public void getNetData() {
        getP().getListData();
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
        return R.layout.fragment_project;
    }

    @Override
    public ProjectPresent newP() {
        return new ProjectPresent();
    }


    public void setTabData(List<ProjectClassifyData> data) {
        for (ProjectClassifyData datum : data) {
            tabProject.addTab(tabProject.newTab().setText(datum.getName()));
        }
        ProjectItemAdapter projectItemAdapter = new ProjectItemAdapter(getChildFragmentManager(), data);
        vpProject.setAdapter(projectItemAdapter);
        tabProject.setupWithViewPager(vpProject);

    }


}
