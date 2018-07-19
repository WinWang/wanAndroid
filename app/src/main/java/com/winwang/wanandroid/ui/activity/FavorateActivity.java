package com.winwang.wanandroid.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.winwang.wanandroid.R;
import com.winwang.wanandroid.base.BaseActivity;

public class FavorateActivity extends BaseActivity {


    @Override
    public void initData(Bundle savedInstanceState) {
        topBar.setTitle("收藏");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_favorate;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void getNetData() {

    }
}
