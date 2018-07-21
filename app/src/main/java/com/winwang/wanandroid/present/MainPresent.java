package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.base.Constant;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.LoginData;
import com.winwang.wanandroid.ui.activity.MainActivity;
import com.winwang.wanandroid.utils.ToastUtil;

import cn.droidlover.xdroidmvp.cache.Sp;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/4/11.
 */

public class MainPresent extends BasePresent<MainActivity> {

    public void getHomeData() {
//        JSONObject json = new JSONObject();
//        json.put("position", 1);
//        String token = SpUtils.getString(getV(), "token", "accessToken");
//        String key = SpUtils.getString(getV(), "token", "secretKey");
//        long timeStamp = System.currentTimeMillis();
//        String sign = StringUtils.encryptToSHA(token + URLConfig.QUERYADVERTISMENT + json.toJSONString() + timeStamp + key);
//        HttpUtils.getInstance().getGankService(URLConfig.BASE_API_URL).queryHomeData(token, URLConfig.QUERYADVERTISMENT, json.toJSONString(), timeStamp, sign, "")
//                .compose(XApi.<BannerBean>getApiTransformer())
//                .compose(XApi.<BannerBean>getScheduler())
//                .compose(getV().<BannerBean>bindToLifecycle())
//                .subscribe(new ApiSubscriber<BannerBean>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        if (error.getType() == 2) { //数据验证异常
//                            //                            getToken();
//                        }
//                    }
//
//                    @Override
//                    public void onSuccess(BannerBean baseModel) {
//
//                        System.out.println(">>>>>>>" + baseModel.getMessage());
//
//                    }
//                });


    }

    public void getUpdateData() {
//        HttpUtils.getInstance().getGankService(URLConfig.BASE_API_URL).getUpdate("com.plyou.leintegration")
//                .compose(XApi.<UpdateBean>getApiTransformer())
//                .compose(XApi.<UpdateBean>getScheduler())
//                .compose(getV().<UpdateBean>bindToLifecycle())
//                .subscribe(new ApiSubscriber<UpdateBean>() {
//                    @Override
//                    protected void onFail(NetError error) {
//
//                    }
//
//                    @Override
//                    protected void onSuccess(UpdateBean updateBean) {
//                        XLog.e(">>>>>" + updateBean.toString());
//                        getV().updataApp(updateBean);
//                    }
//                });
    }

    public void autoLogin(String user, String password) {
        RetrofitManager.getInstance().getApiService()
                .getLoginData(user, password)
                .compose(XApi.<BaseModel<LoginData>>getApiTransformer())
                .compose(XApi.<BaseModel<LoginData>>getScheduler())
                .compose(getV().<BaseModel<LoginData>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<LoginData>>() {
                    @Override
                    protected void onFail(NetError error) {
                        Sp.getInstance(getV()).putBoolean(Constant.LOGIN_STATUS, false);
                        ToastUtil.showToast(error.getMessage());
                    }

                    @Override
                    protected void onSuccess(BaseModel<LoginData> loginDataBaseModel) {
                        LoginData data = loginDataBaseModel.getData();
                        if (loginDataBaseModel.getResultCode() >= 0) {
                            getV().doLoginSuccess(data);
                        }
                    }
                });
    }


}
