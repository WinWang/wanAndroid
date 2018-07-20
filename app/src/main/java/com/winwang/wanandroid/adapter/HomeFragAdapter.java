package com.winwang.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winwang.wanandroid.R;
import com.winwang.wanandroid.model.FeedArticleData;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.imageloader.ILoader;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/5/11.
 */

public class HomeFragAdapter extends BaseQuickAdapter<FeedArticleData, BaseViewHolder> {


    public HomeFragAdapter(int layoutResId, @Nullable List<FeedArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData item) {
        CircleImageView headView = (CircleImageView) helper.getView(R.id.civ_home_head);
        ILoader.Options options = new ILoader.Options(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round);
        ILFactory.getLoader().loadNet(headView, item.getEnvelopePic(), options);
        helper.setText(R.id.tv_home_name, item.getAuthor());
        helper.setText(R.id.tv_home_item_type, item.getSuperChapterName() + "/" + item.getChapterName());
        String desc = item.getDesc();
        if (TextUtils.isEmpty(desc)) {
            helper.setText(R.id.tv_home_desc, item.getTitle());
        } else {
            helper.setText(R.id.tv_home_desc, item.getDesc());
        }
        boolean collect = item.isCollect();
        ImageView likeIcon = (ImageView) helper.getView(R.id.iv_home_favorate);
        if (collect) {
            likeIcon.setImageResource(R.drawable.ic_favorite_checed);
        } else {
            likeIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
    }
}
