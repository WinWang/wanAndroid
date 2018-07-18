package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BannerData;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.model.FeedArticleListData;
import com.winwang.wanandroid.ui.fragment.HomeFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/4/12.
 */

public class HomeFragPresent extends BasePresent<HomeFragment> {

    public void getBannerData() {
        RetrofitManager.getInstance().getApiService().getBannerData()
                .compose(XApi.<BaseModel<List<BannerData>>>getScheduler())
                .compose(getV().<BaseModel<List<BannerData>>>bindToLifecycle())
                .compose(XApi.<BaseModel<List<BannerData>>>getApiTransformer())
                .subscribe(new ApiSubscriber<BaseModel<List<BannerData>>>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel<List<BannerData>> bannerDataBaseModel) {
                        List<BannerData> data = bannerDataBaseModel.getData();
                        getV().setBannerData(data);
                    }
                });
    }

    public void getListData(int pageNum) {
        RetrofitManager.getInstance().getApiService().getFeedArticleList(pageNum)
                .compose(XApi.<BaseModel<FeedArticleListData>>getScheduler())
                .compose(getV().<BaseModel<FeedArticleListData>>bindToLifecycle())
                .compose(XApi.<BaseModel<FeedArticleListData>>getApiTransformer())
                .subscribe(new ApiSubscriber<BaseModel<FeedArticleListData>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(BaseModel<FeedArticleListData> feedArticleListDataBaseModel) {
                        getV().hideLoading();
                        List<FeedArticleData> datas = feedArticleListDataBaseModel.getData().getDatas();
                        getV().setHomeList(datas);
                    }
                });
    }


}
