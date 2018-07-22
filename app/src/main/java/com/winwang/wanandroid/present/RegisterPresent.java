package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.LoginData;
import com.winwang.wanandroid.ui.activity.RegisterActivity;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class RegisterPresent extends BasePresent<RegisterActivity> {
    public void doRegister(String user, String password_1, String password_2) {
        RetrofitManager.getInstance().getApiService()
                .getRegisterData(user, password_1, password_2)
                .compose(XApi.<BaseModel<LoginData>>getScheduler())
                .compose(XApi.<BaseModel<LoginData>>getApiTransformer())
                .compose(getV().<BaseModel<LoginData>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<LoginData>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                        getV().hideDialog();
                    }

                    @Override
                    protected void onSuccess(BaseModel<LoginData> loginDataBaseModel) {
                        getV().registerSuc();
                    }
                });
    }
}
