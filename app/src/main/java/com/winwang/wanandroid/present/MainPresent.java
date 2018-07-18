package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.ui.activity.MainActivity;

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


}
