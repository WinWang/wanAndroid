package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.NavigationListData;
import com.winwang.wanandroid.ui.fragment.NavigationFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class NavigationPresent extends BasePresent<NavigationFragment> {

    public void getNaviData() {
        RetrofitManager.getInstance().getApiService()
                .getNavigationListData()
                .compose(XApi.<BaseModel<List<NavigationListData>>>getScheduler())
                .compose(XApi.<BaseModel<List<NavigationListData>>>getApiTransformer())
                .compose(getV().<BaseModel<List<NavigationListData>>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<List<NavigationListData>>>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel<List<NavigationListData>> listBaseModel) {
                        List<NavigationListData> data = listBaseModel.getData();
                        getV().setNaviData(data);
                    }
                });
    }


}
