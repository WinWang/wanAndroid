package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.ProjectClassifyData;
import com.winwang.wanandroid.ui.fragment.ProjectFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectPresent extends BasePresent<ProjectFragment> {

    public void getListData() {
        RetrofitManager.getInstance().getApiService().getProjectClassifyData()
                .compose(XApi.<BaseModel<List<ProjectClassifyData>>>getApiTransformer())
                .compose(XApi.<BaseModel<List<ProjectClassifyData>>>getScheduler())
                .compose(getV().<BaseModel<List<ProjectClassifyData>>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<List<ProjectClassifyData>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(BaseModel<List<ProjectClassifyData>> listBaseModel) {
                        getV().hideLoading();
                        List<ProjectClassifyData> data = listBaseModel.getData();
                        getV().setTabData(data);
                    }
                });
    }

}
