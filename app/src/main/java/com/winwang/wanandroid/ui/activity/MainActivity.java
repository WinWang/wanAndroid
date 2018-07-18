package com.winwang.wanandroid.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.manager.DownloadManager;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.base.BaseActivity;
import com.winwang.wanandroid.base.BaseLazyFragment;
import com.winwang.wanandroid.event.HomeFragEvent;
import com.winwang.wanandroid.model.UpdateBean;
import com.winwang.wanandroid.present.MainPresent;
import com.winwang.wanandroid.ui.fragment.HomeFragment;
import com.winwang.wanandroid.ui.fragment.KnowledgeFragment;
import com.winwang.wanandroid.ui.fragment.NavigationFragment;
import com.winwang.wanandroid.ui.fragment.ProjectFragment;
import com.winwang.wanandroid.utils.BottomNavigationViewHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity<MainPresent> {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView bottomNavigationBar;
    @BindView(R.id.fragment_group)
    FrameLayout fragmentGroup;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.civ_user_head)
    CircleImageView civUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ll_slide_favorate)
    LinearLayout llSlideFavorate;
    @BindView(R.id.ll_slide_setting)
    LinearLayout llSlideSetting;
    @BindView(R.id.ll_slide_about)
    LinearLayout llSlideAbout;
    @BindView(R.id.ll_slide_logout)
    LinearLayout llSlideLogout;
    private List<BaseLazyFragment> fragmentList = new ArrayList<>();
    private BaseLazyFragment homeFragment;
    private BaseLazyFragment knowledgeFragment;
    private BaseLazyFragment navigationFragment;
    private BaseLazyFragment projectFragment;
    private int lastIndex = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        fragmentList.clear();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationBar);
        homeFragment = new HomeFragment();
        knowledgeFragment = new KnowledgeFragment();
        navigationFragment = new NavigationFragment();
        projectFragment = new ProjectFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(knowledgeFragment);
        fragmentList.add(navigationFragment);
        fragmentList.add(projectFragment);

        loadMultipleRootFragment(R.id.fragment_group, 0,
                homeFragment,
                knowledgeFragment,
                navigationFragment,
                projectFragment);
        topBar.setTitle("首页");
        View button = View.inflate(context, R.layout.home_left_button, null);
        topBar.addLeftView(button, R.id.topbar_left_icon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        showHideFragment(fragmentList.get(0), fragmentList.get(lastIndex));
                        lastIndex = 0;
                        topBar.setTitle("首页");
                        break;
                    case R.id.bottom_knowledge:
                        showHideFragment(fragmentList.get(1), fragmentList.get(lastIndex));
                        lastIndex = 1;
                        topBar.setTitle("知识体系");
                        break;
                    case R.id.bottom_navigation:
                        showHideFragment(fragmentList.get(2), fragmentList.get(lastIndex));
                        lastIndex = 2;
                        topBar.setTitle("导航");
                        break;
                    case R.id.bottom_project:
                        showHideFragment(fragmentList.get(3), fragmentList.get(lastIndex));
                        lastIndex = 3;
                        topBar.setTitle("项目");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public MainPresent newP() {
        return new MainPresent();
    }


    public void updataApp(UpdateBean updateBean) {
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //支持断点下载
                .setBreakpointDownload(true)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置强制更新
                .setForcedUpgrade(false);
        //设置下载过程的监听
        //                .setOnDownloadListener(this);

        DownloadManager manager = DownloadManager.getInstance(this);
        manager.setApkName("appupdate.apk")
                .setApkUrl(updateBean.getDownloadUrl())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setConfiguration(configuration)
                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                .setApkVersionCode(updateBean.getVersionNo())
                .setApkVersionName(updateBean.getVersion())
                .setApkDescription(updateBean.getNotice())
                .download();
    }


    @OnClick({R.id.civ_user_head, R.id.tv_user_name, R.id.ll_slide_favorate, R.id.ll_slide_setting, R.id.ll_slide_about, R.id.ll_slide_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_user_head:

                break;
            case R.id.tv_user_name:

                break;
            case R.id.ll_slide_favorate:

                break;
            case R.id.ll_slide_setting:
                jumpMethod(SettingActivity.class);
                break;
            case R.id.ll_slide_about:

                break;
            case R.id.ll_slide_logout:

                break;
        }
    }


    private void jumpMethod(Class clazz) {
        Router.newIntent(context)
                .to(clazz)
                .launch();
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void changeDayNight(HomeFragEvent event) {
        useDayNight(event.isNight);
    }

}
