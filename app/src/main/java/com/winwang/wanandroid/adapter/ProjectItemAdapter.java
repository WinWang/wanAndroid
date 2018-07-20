package com.winwang.wanandroid.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.winwang.wanandroid.model.ProjectClassifyData;
import com.winwang.wanandroid.ui.fragment.ProjectItemFragment;

import java.util.List;

public class ProjectItemAdapter extends FragmentPagerAdapter {

    List<ProjectClassifyData> data;

    public ProjectItemAdapter(FragmentManager fm, List<ProjectClassifyData> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        ProjectItemFragment projectItemFragment = new ProjectItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("classifyId", data.get(position).getId());
        projectItemFragment.setArguments(bundle);
        return projectItemFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }
}
