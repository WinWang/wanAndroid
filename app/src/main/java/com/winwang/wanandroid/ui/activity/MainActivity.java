package com.winwang.wanandroid.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.base.BaseLazyFragment;
import com.winwang.wanandroid.base.Constant;
import com.winwang.wanandroid.event.HomeFragEvent;
import com.winwang.wanandroid.event.LoginEvent;
import com.winwang.wanandroid.model.LoginData;
import com.winwang.wanandroid.model.UpdateBean;
import com.winwang.wanandroid.present.MainPresent;
import com.winwang.wanandroid.ui.fragment.HomeFragment;
import com.winwang.wanandroid.ui.fragment.KnowledgeFragment;
import com.winwang.wanandroid.ui.fragment.NavigationFragment;
import com.winwang.wanandroid.ui.fragment.ProjectFragment;
import com.winwang.wanandroid.utils.AppManager;
import com.winwang.wanandroid.utils.BottomNavigationViewHelper;
import com.winwang.wanandroid.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.Sp;
import cn.droidlover.xdroidmvp.event.BusFactory;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.router.Router;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;
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
    private int lastIndex = 0;
    //退出时的时间
    private long mExitTime;
    private BaseFragment[] mFragments = new BaseFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUTH = 3;

    @Override
    public void initData(Bundle savedInstanceState) {
        getPerssion();
        initView();
        getNetData();
    }

    private void initView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationBar);
        HomeFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            mFragments[FIRST] = new HomeFragment();
            mFragments[SECOND] = new KnowledgeFragment();
            mFragments[THIRD] = new NavigationFragment();
            mFragments[FOUTH] = new ProjectFragment();

            loadMultipleRootFragment(R.id.fragment_group, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUTH]);
        } else {
            mFragments[FIRST] = fragment;
            mFragments[SECOND] = findFragment(KnowledgeFragment.class);
            mFragments[THIRD] = findFragment(NavigationFragment.class);
            mFragments[FOUTH] = findFragment(ProjectFragment.class);
            showHideFragment(mFragments[FIRST]);
            bottomNavigationBar.setSelectedItemId(R.id.bottom_home);
        }
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
                        showHideFragment(mFragments[FIRST], mFragments[lastIndex]);
                        lastIndex = 0;
                        topBar.setTitle("首页");
                        break;
                    case R.id.bottom_knowledge:
                        showHideFragment(mFragments[SECOND], mFragments[lastIndex]);
                        lastIndex = 1;
                        topBar.setTitle("知识体系");
                        break;
                    case R.id.bottom_navigation:
                        showHideFragment(mFragments[THIRD], mFragments[lastIndex]);
                        lastIndex = 2;
                        topBar.setTitle("导航");
                        break;
                    case R.id.bottom_project:
                        showHideFragment(mFragments[FOUTH], mFragments[lastIndex]);
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
    public void getNetData() {
        String user = Sp.getInstance(context).getString(Constant.LOGIN_USER, "");
        String pass = Sp.getInstance(context).getString(Constant.LOGIN_PASS, "");
        getP().autoLogin(user, pass);
    }

    public void doLoginSuccess(LoginData data) {
        Sp.getInstance(context).putBoolean(Constant.LOGIN_STATUS,true);
        tvUserName.setText(data.getUsername());
        ILoader.Options options = new ILoader.Options(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round);
        ILFactory.getLoader().loadNet(civUserHead, data.getIcon(), options);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public MainPresent newP() {
        return new MainPresent();
    }


    private void getPerssion() {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            ToastUtil.showToast("权限成功");
                        } else {
                            ToastUtil.showToast("权限失败");
                        }
                    }
                });
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
                if (!getLoginStatus()) {
                    jumpMethod(LoginActivity.class);
                }
                break;
            case R.id.tv_user_name:
                if (!getLoginStatus()) {
                    jumpMethod(LoginActivity.class);
                }
                break;
            case R.id.ll_slide_favorate:
                jumpMethod(FavorateActivity.class);
                break;
            case R.id.ll_slide_setting:
                Router.newIntent(context)
                        .to(SettingActivity.class)
                        .requestCode(100)
                        .launch();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            bottomNavigationBar.setSelectedItemId(R.id.bottom_home);
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void changeDayNight(HomeFragEvent event) {
        useDayNight(event.isNight);
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void changeLogin(LoginEvent e) {
        tvUserName.setText(e.data.getUsername());
        ILoader.Options options = new ILoader.Options(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round);
        ILFactory.getLoader().loadNet(civUserHead, e.data.getIcon(), options);
    }


    @Override
    public void onBackPressedSupport() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
            return;
        } else {
            exit();
        }
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            getvDelegate().toastShort("再按一次退出玩Android");
            mExitTime = System.currentTimeMillis();
            return;
        } else {
            AppManager.getAppManager().AppExit(context);
        }
    }
}
