package com.winwang.wanandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.adapter.HomeFragAdapter;
import com.winwang.wanandroid.base.BaseFragment;
import com.winwang.wanandroid.base.BaseLazyFragment;
import com.winwang.wanandroid.model.BannerData;
import com.winwang.wanandroid.model.FeedArticleData;
import com.winwang.wanandroid.present.HomeFragPresent;
import com.winwang.wanandroid.widget.mzbanner.MZBannerView;
import com.winwang.wanandroid.widget.mzbanner.holder.MZHolderCreator;
import com.winwang.wanandroid.widget.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by admin on 2018/4/12.
 */

public class HomeFragment extends BaseFragment<HomeFragPresent> {
    @BindView(R.id.rv_home_frag)
    RecyclerView rvHomeFrag;
    @BindView(R.id.refresh__home)
    SmartRefreshLayout refreshLayout;
    List<FeedArticleData> list = new ArrayList<>();
    private HomeFragAdapter homeFragAdapter;
    private int index = 1;
    private MZBannerView banner;
    private List<BannerData> bannerList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getNetData();
    }

    public void setHomeList(List<FeedArticleData> datas) {
        refreshLayout.finishLoadMore(500);
        refreshLayout.finishRefresh(1000);
        if (index == 1) {
            list.clear();
        }
        list.addAll(datas);
        homeFragAdapter.notifyDataSetChanged();
    }

    public void setBannerData(List<BannerData> list) {
        bannerList.clear();
        bannerList.addAll(list);
        banner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_layout;
    }

    @Override
    public HomeFragPresent newP() {
        return new HomeFragPresent();
    }


    @Override
    public void bindEvent() {
        refreshLayout.setRefreshHeader(new PhoenixHeader(context));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                index++;
                getP().getListData(index);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                index = 1;
                getP().getListData(index);
            }
        });
        rvHomeFrag.setLayoutManager(new LinearLayoutManager(context));
        homeFragAdapter = new HomeFragAdapter(R.layout.item_home_fragment_layout, list);
        rvHomeFrag.setAdapter(homeFragAdapter);
        View inflate = View.inflate(context, R.layout.header_home_frag, null);
        banner = (MZBannerView) inflate.findViewById(R.id.mz_banner_home);
        homeFragAdapter.addHeaderView(inflate);

    }

    @Override
    public void getNetData() {
        getP().getBannerData();
        getP().getListData(index);
    }

    public static class BannerViewHolder implements MZViewHolder<BannerData> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerData data) {
            // 数据绑定
            ILFactory.getLoader().loadNet(mImageView, data.getImagePath(), null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.pause();
    }

}
