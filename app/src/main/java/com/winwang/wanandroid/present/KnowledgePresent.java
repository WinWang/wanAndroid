package com.winwang.wanandroid.present;

import com.winwang.wanandroid.base.BasePresent;
import com.winwang.wanandroid.http.RetrofitManager;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.KnowledgeHierarchyData;
import com.winwang.wanandroid.ui.fragment.KnowledgeFragment;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class KnowledgePresent extends BasePresent<KnowledgeFragment> {

    public void getListData() {
        RetrofitManager.getInstance().getApiService().getKnowledge()
                .compose(XApi.<BaseModel<List<KnowledgeHierarchyData>>>getScheduler())
                .compose(XApi.<BaseModel<List<KnowledgeHierarchyData>>>getApiTransformer())
                .compose(getV().<BaseModel<List<KnowledgeHierarchyData>>>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel<List<KnowledgeHierarchyData>>>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel<List<KnowledgeHierarchyData>> listBaseModel) {

                    }
                });
    }
}
