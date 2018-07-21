package com.winwang.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.base.BaseActivity;
import com.winwang.wanandroid.base.Constant;
import com.winwang.wanandroid.event.LoginEvent;
import com.winwang.wanandroid.model.LoginData;
import com.winwang.wanandroid.present.LoginPresent;
import com.winwang.wanandroid.utils.AppManager;
import com.winwang.wanandroid.utils.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.Sp;
import cn.droidlover.xdroidmvp.event.BusFactory;
import cn.droidlover.xdroidmvp.router.Router;

public class LoginActivity extends BaseActivity<LoginPresent> {


    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.rl_login_password)
    RelativeLayout rlLoginPassword;
    @BindView(R.id.qmrb_login)
    QMUIRoundButton qmrbLogin;
    @BindView(R.id.qmrb_register)
    QMUIRoundButton qmrbRegister;
    private QMUITipDialog qmuiTipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getNetData() {

    }

    public void doLoginSuccess(LoginData data) {
        Sp.getInstance(context).putString(Constant.LOGIN_USER, data.getUsername());
        Sp.getInstance(context).putString(Constant.LOGIN_PASS, data.getPassword());
        BusFactory.getBus().post(new LoginEvent(data));
        AppManager.getAppManager().finishActivity(this);
    }

    public void hideDialog() {
        if (qmuiTipDialog != null) {
            qmuiTipDialog.dismiss();
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresent newP() {
        return new LoginPresent();
    }

    @OnClick({R.id.iv_login_back, R.id.qmrb_login, R.id.qmrb_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                AppManager.getAppManager().finishActivity(this);
                break;
            case R.id.qmrb_login:
                String user = etLoginUser.getText().toString();
                String password = etLoginPassword.getText().toString();
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
                    if (qmuiTipDialog == null) {
                        qmuiTipDialog = DialogUtil.showDialog(context);
                    } else {
                        qmuiTipDialog.show();
                    }
                    getP().doLogin(user, password);
                } else {
                    getvDelegate().toastShort("不能为空哦！！！");
                }
                break;
            case R.id.qmrb_register:
                Router.newIntent(context)
                        .to(RegisterActivity.class)
                        .launch();
                break;
        }
    }
}
