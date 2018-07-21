package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.LoginData;
import com.winwang.wanandroid.ui.activity.LoginActivity;
import com.winwang.wanandroid.utils.ToastUtil;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class LoginPresent extends BasePresent<LoginActivity> {
    public void doLogin(String userName, String password) {
        RetrofitManager.getInstance().getApiService()
                .getLoginData(userName, password)
                .compose(XApi.<BaseModel<LoginData>>getApiTransformer())
                .compose(XApi.<BaseModel<LoginData>>getScheduler())
                .compose(getV().<BaseModel<LoginData>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<LoginData>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().hideDialog();
                        ToastUtil.showToast(error.getMessage());
                    }

                    @Override
                    protected void onSuccess(BaseModel<LoginData> loginDataBaseModel) {
                        getV().hideDialog();
                        LoginData data = loginDataBaseModel.getData();
                        if (loginDataBaseModel.getResultCode() >= 0) {
                            getV().doLoginSuccess(data);
                        }
                    }
                });
    }
}
