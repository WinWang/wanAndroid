package com.winwang.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.base.BaseActivity;
import com.winwang.wanandroid.event.HomeFragEvent;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusFactory;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.qm_topbar)
    QMUITopBar qmTopbar;
    @BindView(R.id.setting_usage_tv)
    TextView settingUsageTv;
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @BindView(R.id.setting_auto_cache_group)
    LinearLayout settingAutoCacheGroup;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.setting_usage_card)
    CardView settingUsageCard;
    @BindView(R.id.setting_other_tv)
    TextView settingOtherTv;
    @BindView(R.id.ll_setting_feedback)
    TextView llSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.setting_other_group)
    CardView settingOtherGroup;
    @BindView(R.id.tv_setting_night)
    TextView tvSettingNight;

    @Override
    public void initData(Bundle savedInstanceState) {
        topBar.setTitle("设置");
        boolean isNight = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ? true : false;
        if (isNight) {
            cbSettingNight.setChecked(true);
        } else {
            cbSettingNight.setChecked(false);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public Object newP() {
        return null;
    }


    @OnClick({R.id.cb_setting_cache, R.id.cb_setting_image, R.id.cb_setting_night, R.id.ll_setting_feedback, R.id.ll_setting_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_setting_cache:
                break;
            case R.id.cb_setting_image:
                break;
            case R.id.cb_setting_night:
//                Sp.getInstance(context).put(Constant.DAY_NIGHT_KEY, defaultNightMode == 1 ? true : false);
                int defaultNightMode = AppCompatDelegate.getDefaultNightMode();
                useDayNight(defaultNightMode == AppCompatDelegate.MODE_NIGHT_YES ? false : true);
                BusFactory.getBus().post(new HomeFragEvent(defaultNightMode == AppCompatDelegate.MODE_NIGHT_YES ? false : true));
                break;
            case R.id.ll_setting_feedback:
                break;
            case R.id.ll_setting_clear:
                break;
        }
    }

    @Override
    public void getNetData() {

    }
}
