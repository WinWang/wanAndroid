package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.model.ProjectListData;
import com.winwang.wanandroid.ui.fragment.ProjectItemFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ProjectItemPresent extends BasePresent<ProjectItemFragment> {

    public void getProjectData(int index, int id) {
        RetrofitManager.getInstance().getApiService()
                .getProjectListData(index, id)
                .compose(XApi.<BaseModel<ProjectListData>>getScheduler())
                .compose(XApi.<BaseModel<ProjectListData>>getApiTransformer())
                .compose(getV().<BaseModel<ProjectListData>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<ProjectListData>>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(BaseModel<ProjectListData> projectListDataBaseModel) {
                        getV().hideLoading();
                        ProjectListData data = projectListDataBaseModel.getData();
                        List<FeedArticleData> datas = data.getDatas();
                        getV().setListData(datas);
                    }
                });
    }
}
