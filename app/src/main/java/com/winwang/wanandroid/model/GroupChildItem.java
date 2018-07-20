package com.winwang.wanandroid.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class GroupChildItem implements MultiItemEntity {

    public List<FeedArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<FeedArticleData> articles) {
        this.articles = articles;
    }

    private List<FeedArticleData> articles;

    @Override
    public int getItemType() {
        return 1;
    }
}
