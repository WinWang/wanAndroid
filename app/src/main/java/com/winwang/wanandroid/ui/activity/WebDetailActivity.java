package com.winwang.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.base.BaseActivity;
import com.winwang.wanandroid.base.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.kit.Kits;

public class WebDetailActivity extends BaseActivity {


    @BindView(R.id.fr_web_container)
    FrameLayout frWebContainer;
    private String artical_url;
    private AgentWeb mAgentWeb;

    @Override
    public void getNetData() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        artical_url = intent.getStringExtra(Constant.ARTICAL_URL);
        String title = intent.getStringExtra(Constant.ARTICAL_Title);
        topBar.setTitle(title);
        initWeb();
    }

    private void initWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(frWebContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(artical_url);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
//        if (mPresenter.getNoImageState()) {
//            mSettings.setBlockNetworkImage(true);
//        } else {
//            mSettings.setBlockNetworkImage(false);
//        }
        if (true) {
            mSettings.setAppCacheEnabled(true);
            mSettings.setDomStorageEnabled(true);
            mSettings.setDatabaseEnabled(true);
            if (Kits.NetWork.hasNetwork(context)) {
                mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            }
        } else {
            mSettings.setAppCacheEnabled(false);
            mSettings.setDomStorageEnabled(false);
            mSettings.setDatabaseEnabled(false);
            mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_detail;
    }

    @Override
    public Object newP() {
        return null;
    }

}
