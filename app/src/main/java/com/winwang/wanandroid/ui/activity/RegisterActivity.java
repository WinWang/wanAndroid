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
import com.winwang.wanandroid.present.RegisterPresent;
import com.winwang.wanandroid.utils.AppManager;
import com.winwang.wanandroid.utils.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresent> {


    @BindView(R.id.iv_register_back)
    ImageView ivRegisterBack;
    @BindView(R.id.et_register_user)
    EditText etRegisterUser;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.rl_register_password)
    RelativeLayout rlRegisterPassword;
    @BindView(R.id.et_register_password_confirm)
    EditText etRegisterPasswordConfirm;
    @BindView(R.id.rl_register_password_confirm)
    RelativeLayout rlRegisterPasswordConfirm;
    @BindView(R.id.qmrb_register_confirm)
    QMUIRoundButton qmrbRegisterConfirm;
    private QMUITipDialog qmuiTipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getNetData() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresent newP() {
        return new RegisterPresent();
    }

    public void registerSuc() {
        if (qmuiTipDialog != null) {
            qmuiTipDialog.dismiss();
        }
        AppManager.getAppManager().finishActivity(this);
    }


    @OnClick({R.id.iv_register_back, R.id.qmrb_register_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back:
                AppManager.getAppManager().finishActivity(this);
                break;
            case R.id.qmrb_register_confirm:
                String user = etRegisterUser.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String passwordConfirm = etRegisterPasswordConfirm.getText().toString();
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConfirm)) {
                    if (qmuiTipDialog == null) {
                        qmuiTipDialog = DialogUtil.showDialog(context);
                    } else {
                        qmuiTipDialog.show();
                    }
                    getP().doRegister(user, password, passwordConfirm);
                }else {
                    getvDelegate().toastShort("不能为空哦！！！");
                }
                break;
        }
    }
}
