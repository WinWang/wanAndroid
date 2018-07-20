package com.winwang.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.FeedArticleData;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.kit.Kits;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProjectListAdapter extends BaseQuickAdapter<FeedArticleData, BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_pv_item_cover);
        ILoader.Options options = new ILoader.Options(R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        ILFactory.getLoader().loadNet(cover, item.getEnvelopePic(), options);
        helper.setText(R.id.tv_pv_item_title, item.getTitle());
        helper.setText(R.id.tv_pv_item_desc, item.getDesc());
        String md = Kits.Date.getMd(item.getPublishTime());
        helper.setText(R.id.tv_pv_item_data_user, md + "  " + item.getAuthor());
    }
}
